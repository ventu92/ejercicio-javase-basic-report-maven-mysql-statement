package es.neodoo.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.Statement;
import es.neodoo.model.vo.TeacherVO;
import es.neodoo.model.vo.UserTeacherVO;
import es.neodoo.service.OperationDB;

public class ReportDAO extends OperationDB {
	private Connection conexion = null;
	private OperationDB operationDB = null;

	public List<TeacherVO> getLstTeacher() {

		List<TeacherVO> teachers = new ArrayList();
		try {

			// Se realiza la consulta. Los resultados se guardan en el
			Statement s = (Statement) conexion.createStatement();

			ResultSet rs = s.executeQuery("select * from user");

			// Se recorre el ResultSet, mostrando por pantalla los resultados.
			while (rs.next()) {

				TeacherVO teacher = null;

				teacher = new TeacherVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				teachers.add(teacher);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teachers;

	}

	public void insertTeachers() {

		try {

			// Se realiza la consulta. Los resultados se guardan en el
			Statement s1 = (Statement) conexion.createStatement();
			Statement s2 = (Statement) conexion.createStatement();

			int update = s1.executeUpdate(
					"INSERT INTO user (nombre ,apellidos , dni)" + "VALUES('fernando','martinez','13468045J');");
			if (update == 1) {
				update = 0;
				update = s2.executeUpdate(
						"INSERT INTO user_teacher (centro,asignatura)" + "VALUES('Salesianos','Fisica');");
				if (update == 1) {
					System.out.println(
							"Se ha realizado correctamente la insercion de los datos en la tabla USER_TEACHER");
				} else {
					System.out.println(
							"no se ha realizado correctamente la insercion de los datos en la tabla USER_TEACHER");
				}
			} else {
				System.out.println("NO se ha realizado correctamente la insercion de los datos en la tabla USER");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteData() {

		try {
			// Se realiza la consulta. Los resultados se guardan en el
			Statement s1 = (Statement) conexion.createStatement();

			boolean delete = s1.execute("DELETE FROM user WHERE nombre = 'fernando';");

			if (delete == false) {
				System.out.println("Se ha realizado correctamente el borrado de los datos en la tabla USER");
			} else {
				System.out.println("NO se ha realizado correctamente el borrado de los datos en la tabla USER");
			}

			// Se cierra la conexión con la base de datos.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initConection() {

		OperationDB operationDB = new OperationDB();
		this.conexion = operationDB.conectar();
	}

	public void closeConection() {
		operationDB.desconectar(conexion);
	}
}
