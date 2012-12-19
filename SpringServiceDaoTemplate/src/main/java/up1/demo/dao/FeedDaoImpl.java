package up1.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import up1.demo.value.FeedValue;

@Repository
public class FeedDaoImpl implements FeedDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	@Transactional
	public void createFeed(FeedValue feedValue) {
		String sql = "INSERT INTO FEED( name, description, link, create_date ) VALUES ( ?,?,?, NOW() )";
		jdbcTemplate.update(sql, new Object[] { feedValue.getName(), feedValue.getLongDescription(), feedValue.getLink() });
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@Transactional
	public FeedValue getFeed(int id) {
		String sql = "SELECT * FROM FEED WHERE ID = ?";
		return (FeedValue) jdbcTemplate.queryForObject(sql, new Object[] { Integer.valueOf(id) }, 
				new RowMapper() {
					public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						FeedValue feedValue = new FeedValue();
						feedValue.setId(resultSet.getInt("ID"));
						feedValue.setName(resultSet.getString("NAME"));
						return feedValue;
					}
				});
	}

}
