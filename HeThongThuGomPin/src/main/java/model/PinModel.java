package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import entity.ChiNhanh;
import entity.GioHang;
import entity.KhachHang;
import entity.Pin;
import entity.ThongTinDatLich;
import entity.ThuongHieu;


public class PinModel {
	private DataSource dataSource;

	public PinModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	public List<Pin> getALLPin() {
	    List<Pin> dsPin = new ArrayList<Pin>();
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet result = null;

	    try {
	        connection = dataSource.getConnection();
	        String sqlQuery = "SELECT * FROM Pin";
	        statement = connection.prepareStatement(sqlQuery);
	        result = statement.executeQuery();

	        while (result.next()) {
	            int maPin = result.getInt("maPin");
	            String tenPin = result.getString("tenPin");
	            float giaThu = result.getFloat("giaThu");
	            int soLuong = result.getInt("soLuong");
	            String moTa = result.getString("moTa");
	            int thuongHieuId = result.getInt("thuongHieuId");
	            String hinhAnh = result.getString("hinhAnh");

	            Pin pin = new Pin(maPin, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
	            dsPin.add(pin);
	        }
	        return dsPin;
	    } catch (Exception e) {
	        // Xử lý ngoại lệ ở đây
	    } finally {
	        close(connection, statement, result);
	    }
	    return dsPin;
	}
	
	//Thông tin đặt lịch
	public List<ThongTinDatLich> getALLDatLich() {
	    List<ThongTinDatLich> dsDatLich = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet result = null;

	    try {
	        connection = dataSource.getConnection();
	        String sqlQuery = "SELECT * FROM ThongTinDatLich";
	        statement = connection.prepareStatement(sqlQuery);
	        result = statement.executeQuery();

	        while (result.next()) {
	            int maDatLich = result.getInt("maDatLich");
	            String tenKH = result.getString("tenKH");
	            String soDT = result.getString("soDT");
	            String diaChi = result.getString("diaChi");
	            String thoiGian = result.getString("thoiGian");
	            String ngayHen = result.getString("ngayHen");
	            String moTa = result.getString("moTa");  
	            int maPin = result.getInt("maPin");
	            int maChiNhanh = result.getInt("maChiNhanh");

	            ThongTinDatLich thongTinDatLich = new ThongTinDatLich(maDatLich, tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), moTa);
	            dsDatLich.add(thongTinDatLich);
	        }
	    } catch (Exception e) {
	        // Log or handle exception
	        e.printStackTrace();
	    } finally {
	        close(connection, statement, result);
	    }
	    return dsDatLich;
	}
	
		public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			try {
				if (myRs != null) {
					myRs.close(); }			
				if (myStmt != null) {
					myStmt.close();	}			
				if (myConn != null) {
					myConn.close();   
					// doesn't really close it ... just puts back in connection pool
				}
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		
		
//		public DongHo getDongHo(String maDongHo) throws Exception {
//			DongHo dongho = null;	
//			Connection myConn = null;
//			PreparedStatement myStmt = null;
//			ResultSet myRs = null;
//			try {
//				
//				// get connection to database
//				myConn = dataSource.getConnection();
//				// create sql to get selected product
//				String sql = "Select * from DongHo where maDH = " + maDongHo;			
//				// create prepared statement
//				myStmt = myConn.prepareStatement(sql);					
//				// execute statement
//				myRs = myStmt.executeQuery();			
//				// retrieve data from result set row
//				if (myRs.next()) {
//					int maDH = myRs.getInt("maDH");
//					String tenDH = myRs.getString("tenDH");
//					float giaBan = myRs.getFloat("giaBan");
//					int soLuong = myRs.getInt("soLuong");
//					String moTa = myRs.getString("moTa");
//					int thuongHieuId = myRs.getInt("thuongHieuId");
//					String hinhAnh = myRs.getString("hinhAnh");
//					
//					//đư ảnh từ csdl sql server lên đây
//					
//				 dongho = new DongHo(maDH,tenDH, giaBan,soLuong, moTa, thuongHieuId, hinhAnh);
//				}
//				else {
//					throw new Exception("Could not find DongHo with maDH = " + maDongHo);	}							
//				return dongho;
//			}
//			finally {
//				// clean up JDBC objects
//				close(myConn, myStmt, myRs);}
//		}

		
		//update
		public void loadChiTiet(Pin pin) {
			Connection connection = null;
			PreparedStatement statement = null;
			try {
				connection = dataSource.getConnection();
				String sqlQuery = "UPDATE Pin " + 
		                 "SET tenPin=?, giaThu=?, moTa=?, hinhAnh=? " +
		                 "WHERE maTT=?";
				statement = connection.prepareStatement(sqlQuery);
				statement.setString(1, pin.getTenPin());
				statement.setFloat(2, pin.getGiaThu());
				statement.setString(3, pin.getMoTa());
				statement.setString(4, pin.getHinhAnh()); 
				statement.setInt(5, pin.getMaPin()); 
				statement.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(connection, statement, null);
			}
		}
		
		public Pin getPinByid(int maPin) {
			Statement state = null;
			ResultSet rs;
			Connection con;
			try {
				String sql = "Select * from PIN where maPin = " + maPin;
				con = dataSource.getConnection();
				state = con.createStatement();
				rs = state.executeQuery(sql);
				while (rs.next()) {
					 int maPins = rs.getInt("maPin");
			            String tenPin = rs.getString("tenPin");
			            float giaBan = rs.getFloat("giaThu");
			            int soLuong = rs.getInt("soLuong");
			            String moTa = rs.getString("moTa");
			            int thuongHieuId = rs.getInt("thuongHieuId");
			            String hinhAnh = rs.getString("hinhAnh");

			            Pin pin = new Pin(maPins, tenPin, giaBan, soLuong, moTa, thuongHieuId, hinhAnh);
			            return pin;
			        }
			        
				
			} 
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}
		
		
//		//THEM SUA XOA
//		public List<Pin> getALLDongHoSortedByPriceAscending() {
//		    List<DongHo> dsDongHo = new ArrayList<DongHo>();
//		    Connection connection = null;
//		    PreparedStatement statement = null;
//		    ResultSet result = null;
//
//		    try {
//		        connection = dataSource.getConnection();
//		        String sqlQuery = "SELECT * FROM DongHo ORDER BY giaBan ASC";
//		        statement = connection.prepareStatement(sqlQuery);
//		        result = statement.executeQuery();
//
//		        while (result.next()) {
//		            int maDH = result.getInt("maDH");
//		            String tenDH = result.getString("tenDH");
//		            float giaBan = result.getFloat("giaBan");
//		            int soLuong = result.getInt("soLuong");
//		            String moTa = result.getString("moTa");
//		            int thuongHieuId = result.getInt("thuongHieuId");
//		            String hinhAnh = result.getString("hinhAnh");
//
//		            DongHo dongho = new DongHo(maDH, tenDH, giaBan,soLuong, moTa, thuongHieuId, hinhAnh);
//		            dsDongHo.add(dongho);
//		        }
//		        return dsDongHo;
//		    } 
//		    catch (Exception e) {
//		        e.printStackTrace();
//		    } 
//		    finally {
//		        close(connection, statement, result);
//		    }
//		    return dsDongHo;
//		}
		
//		public List<DongHo> getALLDongHoSortedByPriceDescending() {
//		    List<DongHo> dsDongHo = new ArrayList<DongHo>();
//		    Connection connection = null;
//		    PreparedStatement statement = null;
//		    ResultSet result = null;
//
//		    try {
//		        connection = dataSource.getConnection();
//		        String sqlQuery = "SELECT * FROM DongHo ORDER BY giaBan DESC";
//		        statement = connection.prepareStatement(sqlQuery);
//		        result = statement.executeQuery();
//
//		        while (result.next()) {
//		            int maDH = result.getInt("maDH");
//		            String tenDH = result.getString("tenDH");
//		            float giaBan = result.getFloat("giaBan");
//		            int soLuong = result.getInt("soLuong");
//		            String moTa = result.getString("moTa");
//		            int thuongHieuId = result.getInt("thuongHieuId");
//		            String hinhAnh = result.getString("hinhAnh");
//
//		            DongHo dongho = new DongHo(maDH, tenDH, giaBan,soLuong, moTa, thuongHieuId, hinhAnh);
//		            dsDongHo.add(dongho);
//		        }
//		        return dsDongHo;
//		    } 
//		    catch (Exception e) {
//		        e.printStackTrace();
//		    } 
//		    finally {
//		        close(connection, statement, result);
//		    }
//		    return dsDongHo;
//		}
		
		public List<Pin> searchPinByName(String inputTenPin) {
			// TODO Auto-generated method stub
			List<Pin> dsPin = new ArrayList<Pin>();
		    Connection connection = null;
		    PreparedStatement statement = null;
		    ResultSet result = null;

		    try {
		        connection = dataSource.getConnection();
		        String sqlQuery = "SELECT * FROM Pin WHERE tenPin LIKE ?";
		        statement = connection.prepareStatement(sqlQuery);
		        statement.setString(1, "%" + inputTenPin + "%");
		        result = statement.executeQuery();

		        while (result.next()) {
		        	 int maPin = result.getInt("maPin");
			            String tenPin = result.getString("tenPin");
			            float giaThu = result.getFloat("giaThu");
			            int soLuong = result.getInt("soLuong");
			            String moTa = result.getString("moTa");
			            int thuongHieuId = result.getInt("thuongHieuId");
			            String hinhAnh = result.getString("hinhAnh");

			            Pin pin = new Pin(maPin, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
		            dsPin.add(pin);
		        }
		        return dsPin;
		    } 
		    catch (Exception e) {
		        e.printStackTrace();
		    } 
		    finally {
		        close(connection, statement, result);
		    }
		    return dsPin;
		}
		
		//thêm
		public void addThongTinDatLich(ThongTinDatLich datLich) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = dataSource.getConnection();
		        String sqlQuery = "INSERT INTO ThongTinDatLich (tenKH, soDT, diaChi, thoiGian, ngayHen, moTa, maPin, maChiNhanh) "
		                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		        stmt = conn.prepareStatement(sqlQuery);
		        stmt.setString(1, datLich.getTenKH());
		        stmt.setString(2, datLich.getSoDT()); // Make sure you provide a non-null value for 'soDT'
		        stmt.setString(3, datLich.getDiaChi());
		        stmt.setString(4, datLich.getThoiGian());
		        stmt.setString(5, datLich.getNgayHen());
		        stmt.setString(6, datLich.getMoTa());
		        stmt.setInt(7, datLich.getPin().getMaPin());
		        stmt.setInt(8, datLich.getChiNhanh().getMaChiNhanh());
		        stmt.executeUpdate();
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        close(conn, stmt, null);
		    }
		}

		
		public void deleteThongTinDatLich(String id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				int maDatLich = Integer.parseInt(id);
				conn = dataSource.getConnection();
				String sql = "DELETE FROM ThongTinDatLich WHERE maDatLich=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, maDatLich);
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(conn, stmt, null);
			}
		}
		
		public void updateThongTinDatLich(ThongTinDatLich datLich) {
			Connection conn = null;
			PreparedStatement stmt = null;
			try {
				conn = dataSource.getConnection();
				String sqlQuery = "UPDATE ThongTinDatLich "
						+ "SET tenKH=?, soDT=?, diaChi=?, thoiGian=?, ngayHen=?, moTa=?, maPin=?, maChiNhanh=? "
						+ "WHERE maDatLich";
				 stmt = conn.prepareStatement(sqlQuery);
			        stmt.setString(1, datLich.getTenKH());
			        stmt.setString(2, datLich.getSoDT()); // Make sure you provide a non-null value for 'soDT'
			        stmt.setString(3, datLich.getDiaChi());
			        stmt.setString(4, datLich.getThoiGian());
			        stmt.setString(5, datLich.getNgayHen());
			        stmt.setString(6, datLich.getMoTa());
			        stmt.setInt(7, datLich.getPin().getMaPin());
			        stmt.setInt(8, datLich.getChiNhanh().getMaChiNhanh());
				stmt.execute();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		//thêm Thông Tin Pin (Chua xong)
				public void addThongTinPin(Pin pin) {
				    Connection conn = null;
				    PreparedStatement stmt = null;
				    try {
				        conn = dataSource.getConnection();
				        String sqlQuery = "INSERT INTO Pin ( tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh) "
				                         + "VALUES (?, ?, ?, ?, ?, ?)";
				        stmt = conn.prepareStatement(sqlQuery);
				        //stmt.setInt(1, pin.getMaPin());
				        stmt.setString(1, pin.getTenPin()); // Make sure you provide a non-null value for 'soDT'
				        stmt.setFloat(2, pin.getGiaThu());
				        stmt.setInt(3, pin.getSoLuong());
				        stmt.setString(4, pin.getMoTa());
				        stmt.setInt(5, pin.getThuongHieuId());
				        stmt.setString(6, pin.getHinhAnh());
				       
				        stmt.executeUpdate();
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        close(conn, stmt, null);
				    }
				}
				
				// sửa pin
				public void updatePin(Pin p) {
					Connection conn = null;
					PreparedStatement stmt = null;
					try {
						conn = dataSource.getConnection();
						String sqlQuery = "UPDATE Pin "
								+ "SET tenPin=?, giaThu=?, soLuong=?, moTa=?, thuongHieuId=?, hinhAnh=?, maPin=? "
								+ "WHERE maPin";
						 stmt = conn.prepareStatement(sqlQuery);
					        stmt.setString(1, p.getTenPin());
					        stmt.setFloat(2, p.getGiaThu()); // Make sure you provide a non-null value for 'soDT'
					        stmt.setInt(3, p.getSoLuong());
					        stmt.setString(4, p.getMoTa());
					        stmt.setInt(5, p.getThuongHieuId());
					        stmt.setString(6, p.getHinhAnh());
					        stmt.setInt(7, p.getMaPin());
						stmt.execute();
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				
				
				//xoa pin
				public void deletePin(String id) {
					Connection conn = null;
					PreparedStatement stmt = null;
					try {
						int maPin = Integer.parseInt(id);
						conn = dataSource.getConnection();
						String sql = "DELETE FROM Pin WHERE maPin=?";
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, maPin);
						stmt.execute();
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					finally {
						close(conn, stmt, null);
					}
				}
				
				
				public Pin getPin(String maPin) {
				    Pin pin = null;
				    Connection myConn = null;
				    PreparedStatement myStmt = null;
				    ResultSet myRs = null;
				    try {
				        // get connection to database
				        myConn = dataSource.getConnection();
				        // create sql to get selected product
				        String sql = "Select * from Pin where maPin = ?";
				        // create prepared statement
				        myStmt = myConn.prepareStatement(sql);
				        myStmt.setString(1, maPin);
				        // execute statement
				        myRs = myStmt.executeQuery();
				        // retrieve data from result set row
				        if (myRs.next()) {
				            //int ma = myRs.getInt("maPin");
				            String tenPin = myRs.getString("tenPin");
				            String giaThu = myRs.getString("soDT");
				            String soLuong = myRs.getString("thoiGian");
				            String moTa = myRs.getString("ngayHen");
				            String thuongHieuId = myRs.getString("thuongHieuId"); 
				            String hinhAnh = myRs.getString("hinhAnh");
				            int ma = myRs.getInt("maPin");
//				            int maChiNhanh = myRs.getInt("maChiNhanh");
//				            String moTa = myRs.getString("moTa");
				            
				            
				            // Create ThongTinDatLich object
				            pin = new Pin(ma, tenPin, ma, ma, moTa, ma, hinhAnh);
				        } else {
				            throw new Exception("Could not find ThongTinDatLich with maDatLich = " + maPin);
				        }
				        return pin;
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        // clean up JDBC objects
				        close(myConn, myStmt, myRs);
				    }
				    return null;
				}
		
		
				public ThongTinDatLich getDatLich(String maDatLich) {
				    ThongTinDatLich datLich = null;
				    Connection myConn = null;
				    PreparedStatement myStmt = null;
				    ResultSet myRs = null;
				    try {
				        // get connection to database
				        myConn = dataSource.getConnection();
				        // create sql to get selected product
				        String sql = "Select * from ThongTinDatLich where maDatLich = ?";
				        // create prepared statement
				        myStmt = myConn.prepareStatement(sql);
				        myStmt.setString(1, maDatLich);
				        // execute statement
				        myRs = myStmt.executeQuery();
				        // retrieve data from result set row
				        if (myRs.next()) {
				            int maDLich = myRs.getInt("maDatLich");
				            String tenKH = myRs.getString("tenKH");
				            String soDT = myRs.getString("soDT");
				            String thoiGian = myRs.getString("thoiGian");
				            String ngayHen = myRs.getString("ngayHen");
				            int maPin = myRs.getInt("maPin");
				            int maChiNhanh = myRs.getInt("maChiNhanh");
				            String moTa = myRs.getString("moTa");
				            
				            
				            // Create ThongTinDatLich object
				            datLich = new ThongTinDatLich(maDLich, tenKH, soDT, soDT, thoiGian, ngayHen, maChiNhanh, maPin, moTa);
				        } else {
				            throw new Exception("Could not find ThongTinDatLich with maDatLich = " + maDatLich);
				        }
				        return datLich;
				    } catch (Exception e) {
				        e.printStackTrace();
				    } finally {
				        // clean up JDBC objects
				        close(myConn, myStmt, myRs);
				    }
				    return null;
				}

		
		//THUONG HIEU
		public List<ThuongHieu> getAllBrands() {
			List<ThuongHieu> listThuongHieu = new ArrayList<ThuongHieu>();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = dataSource.getConnection();
				String sql = "SELECT * FROM ThuongHieu";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while (rs.next()) {
					int thuongHieuId = rs.getInt("thuongHieuId");
					String name = rs.getString("name");
					ThuongHieu thuongHieu = new ThuongHieu(thuongHieuId, name);
					listThuongHieu.add(thuongHieu);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			finally {
				close(conn, stmt, rs);
			}
			return listThuongHieu;
		}

//		public List<DongHo> getDongHoByBrand(int id) {
//			// TODO Auto-generated method stub
//			List<DongHo> listDongHo = new ArrayList<DongHo>();
//			Connection conn = null;
//			PreparedStatement stmt = null;
//			ResultSet rs = null;
//			try {
//				conn = dataSource.getConnection();
//				String sqlQuery = "SELECT * FROM DongHo WHERE thuongHieuId = ?";
//				stmt = conn.prepareStatement(sqlQuery);
//				stmt.setInt(1, id);
//				rs = stmt.executeQuery();
//				while (rs.next()) {
//		            int maDH = rs.getInt("maDH");
//		            String tenDH = rs.getString("tenDH");
//		            float giaBan = rs.getFloat("giaBan");
//		            int soLuong = rs.getInt("soLuong");
//		            String moTa = rs.getString("moTa");
//		            int thuongHieuId = rs.getInt("thuongHieuId");
//		            String hinhAnh = rs.getString("hinhAnh");
//		            DongHo dongho = new DongHo(maDH, tenDH, giaBan,soLuong, moTa, thuongHieuId, hinhAnh);
//		            listDongHo.add(dongho);
//		        }
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			finally {
//				close(conn, stmt, rs);
//			}
//			return listDongHo;
//		}
		
	
}
