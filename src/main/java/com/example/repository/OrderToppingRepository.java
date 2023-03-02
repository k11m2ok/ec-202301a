package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.form.ShoppingCartForm;

@Repository
public class OrderToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 注文トッピング情報を挿入する.
	 * 
	 * @param shoppingCartForm 商品詳細画面の入力値
	 * @param orderItemId　トッピング情報と紐づく商品ID
	 */
	public void insert(ShoppingCartForm shoppingCartForm, Integer orderItemId) {
		String insertSql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId);";
		for (Integer toppingId : shoppingCartForm.getToppingIdList()) {
			SqlParameterSource param = new MapSqlParameterSource().addValue("toppingId", toppingId)
					.addValue("orderItemId", orderItemId);
			template.update(insertSql, param);
		}
	}
}
