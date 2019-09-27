package com.ecommerce.product.category;

import com.ecommerce.shared.jackson.DefaultObjectMapper;
import com.ecommerce.shared.model.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;

@Component
public class CategoryRepository extends BaseRepository<Category> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DefaultObjectMapper objectMapper;

    public CategoryRepository(NamedParameterJdbcTemplate jdbcTemplate,
                              DefaultObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    protected void doSave(Category category) {
        String sql = "INSERT INTO CATEGORY (ID, JSON_CONTENT) VALUES (:id, :json) " +
                "ON DUPLICATE KEY UPDATE JSON_CONTENT=:json;";
        Map<String, String> paramMap = of("id", category.getId(), "json", objectMapper.writeValueAsString(category));
        jdbcTemplate.update(sql, paramMap);
    }

    public Category byId(String id) {
        try {
            String sql = "SELECT JSON_CONTENT FROM CATEGORY WHERE ID=:id;";
            return jdbcTemplate.queryForObject(sql, of("id", id), mapper());
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(id);
        }
    }


    private RowMapper<Category> mapper() {
        return (rs, rowNum) -> objectMapper.readValue(rs.getString("JSON_CONTENT"), Category.class);
    }
}
