package com.bit2016.mysite.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.bit2016.mysite.vo.GuestBookVo;

public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public static Connection connection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";

			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		return conn;
	}

	public GuestBookVo get(Long no) {
		GuestBookVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = connection();

			String sql = "select no,name,content,to_char(reg_date,'yyyy-mm-dd hh:mi:ss') from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new GuestBookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setContent(rs.getString(3));
				vo.setDate(rs.getString(4));
			}

		} catch (SQLException e) {
			System.out.println(e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return vo;
	}

	public Long insert(GuestBookVo vo) {
		Long no = 1L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = connection();

			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,sysdate,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPassWord());

			pstmt.executeQuery();

			// primary key ( guestbook_seq.currval) 받아오기
			stmt = conn.createStatement();
			sql = "select guestbook_seq.currval from dual";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				no = rs.getLong(1);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return no;
	}

	public ArrayList<GuestBookVo> getList() {
		List<GuestBookVo> list=sqlSession.selectList("getstbook.getList");
		return (ArrayList<GuestBookVo>) list;
	}

	// ajax 리스트
	public List<GuestBookVo> getList(int page) {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = connection();

			String sql = "select *" + "from(select rownum rn,a.*" + "from(select no," + "name," + "content,"
					+ "password, " + "to_char(up_date,'yyyy-mm-dd hh:mi:ss') as reg_date " + "from guestbook "
					+ "order by no desc) a)" + "where (?-1)*5+1<=rn and rn<=?*5";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, page);
			pstmt.setInt(2, page);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long no = rs.getLong(2);
				String name = rs.getString(3);
				String context = rs.getString(4);
				String passwd = rs.getString(5);
				String regDate = rs.getString(6);

				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setContent(context);
				vo.setPassWord(passwd);
				vo.setDate(regDate);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				System.out.println("error" + e);
			}
		}
		return list;
	}

	public static boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = connection();

			String sql = "delete from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return result == 1;
	}
}
