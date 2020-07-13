package com.master.application.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.master.application.bean.BlogUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class BlogJwtUtil {

	private static String key = "777";
	private static ThreadLocal<Jwts> Jwt = new ThreadLocal<Jwts>();

	public static synchronized String createJwtToekn(BlogUser user) {

		long now = System.currentTimeMillis();

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("usercode", user.getUserCode());
		claims.put("password", user.getPassword());
		JwtBuilder builder = Jwt.get().builder().setClaims(claims).setId(user.getId() + "")
				.setIssuedAt(new Timestamp(now)).setSubject("BanbanBlogToken").signWith(SignatureAlgorithm.HS256, key);

		long expMillis = now + 604800000L; // 7天过期时间
		Timestamp tamp = new Timestamp(expMillis);
		builder.setExpiration(tamp);

		return builder.compact();
	}

	public static synchronized BlogUser parseJwtToken(String token) throws Exception {

		Claims c = Jwt.get().parser().setSigningKey(key).parseClaimsJws(token).getBody();

		String id = c.getId();
		String usercode = (String) c.get("usercode");
		String password = (String) c.get("password");

		BlogUser user = new BlogUser();
		user.setId(Integer.parseInt(id));
		user.setUserCode(usercode);
		user.setPassword(password);

		return user;
	}

	public static void main(String[] args) {

		BlogUser u = new BlogUser();
		u.setId(1);
		u.setUserCode("admin");
		u.setPassword("123sdad");

		String token = createJwtToekn(u);

		System.out.println(token);

		try {
			BlogUser user = parseJwtToken(token);
			System.out.println(user.getUserCode());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
