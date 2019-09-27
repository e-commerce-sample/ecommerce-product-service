package com.ecommerce.product.product;

import com.ecommerce.product.sdk.representation.product.ProductRepresentation;
import com.ecommerce.product.sdk.representation.product.ProductSummaryRepresentation;
import com.ecommerce.product.sdk.representation.product.ProductWithCategoryRepresentation;
import com.ecommerce.shared.utils.PagedResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Maps.newHashMap;

@Component
public class ProductRepresentationService {
    private static final String SELECT_SQL = "SELECT ID, NAME, PRICE FROM PRODUCT LIMIT :limit OFFSET :offset;";
    private static final String COUNT_SQL = "SELECT COUNT(1) FROM PRODUCT;";

    // CHECKSTYLE:OFF
    private static final String PRODUCT_WITH_CATEGORY_SQL = "SELECT PRODUCT.ID, PRODUCT.NAME, CATEGORY.ID AS CATEGORY_ID, CATEGORY.NAME AS CATEGORY_NAME FROM PRODUCT JOIN CATEGORY ON PRODUCT.CATEGORY_ID=CATEGORY.ID WHERE PRODUCT.ID=:productId;";
    // CHECKSTYLE:ON

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ProductRepository repository;

    public ProductRepresentationService(NamedParameterJdbcTemplate jdbcTemplate,
                                        ProductRepository repository) {
        this.jdbcTemplate = jdbcTemplate;
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public ProductRepresentation byId(String id) {
        return repository.byId(id).toRepresentation();
    }

    @Transactional(readOnly = true)
    public PagedResource<ProductSummaryRepresentation> listProducts(int pageIndex, int pageSize) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("limit", pageSize);
        parameters.addValue("offset", (pageIndex - 1) * pageSize);

        List<ProductSummaryRepresentation> products = jdbcTemplate.query(SELECT_SQL, parameters,
                (rs, rowNum) -> new ProductSummaryRepresentation(rs.getString("ID"),
                        rs.getString("NAME"),
                        rs.getBigDecimal("PRICE")));

        int total = jdbcTemplate.queryForObject(COUNT_SQL, newHashMap(), Integer.class);
        return PagedResource.of(total, pageIndex, products);
    }

    @Transactional(readOnly = true)
    public ProductWithCategoryRepresentation productWithCategory(String id) {
        return jdbcTemplate.queryForObject(PRODUCT_WITH_CATEGORY_SQL, of("productId", id),
                (rs, rowNum) -> new ProductWithCategoryRepresentation(rs.getString("ID"),
                        rs.getString("NAME"),
                        rs.getString("CATEGORY_ID"),
                        rs.getString("CATEGORY_NAME")));
    }


}
