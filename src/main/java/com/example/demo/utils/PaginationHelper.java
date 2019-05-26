package com.example.demo.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class PaginationHelper<T> {

    public Page<T> fetchPage(
            final JdbcTemplate jt,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object args[],
            final int pageNo,
            final int pageSize,
            final RowMapper<T> rowMapper) {

        // determine how many rows are available
        final int rowCount = jt.queryForObject(sqlCountRows, Integer.class);

        // calculate the number of pages
        int pageCount = rowCount / pageSize;
        if (rowCount > pageSize * pageCount) {
            pageCount++;
        }

        // create the page object
        final Page<T> page = new Page<>();
        page.setTotalItems(rowCount);
        page.setPageNumber(pageNo);
        page.setPagesAvailable(pageCount);

        // fetch a single page of results
        final int startRow = (pageNo - 1) * pageSize;
        jt.query(
                sqlFetchRows + " LIMIT " + pageSize + " OFFSET " + startRow,
                args,
                (ResultSet rs) -> {
                        final List pageItems = page.getPageItems();
                        int currentRow = 0;
                        while (rs.next()) {
                                pageItems.add(rowMapper.mapRow(rs, currentRow));
                            currentRow++;
                        }
                        return page;
                    }
                );
        return page;
    }

}
