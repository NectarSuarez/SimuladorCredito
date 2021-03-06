package com.credito.ba.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.credito.ba.VO.plazo;
import com.credito.ba.VO.Producto;

@Repository
public class creditoDAO
{

	@Autowired
	DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	private final String SetProducto  		= "SetProducto";
	private final String GetProducto  		= "GetProducto";
	private final String UpdateProducto  	= "UpdateProducto";
	private final String DeleteProducto  	= "DeleteProducto";
	private final String SetPlazo  			= "SetPlazo";
	private final String GetPlazo  			= "GetPlazo";
	private final String UpdatePlazo  		= "UpdatePlazo";
	private final String DeletePlazo  		= "DeletePlazo";
	
	private String Cursor = "Cursor";
	
	public String SetProducto(Producto prod)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inSKU", prod.getSku())
					.addValue("inNombre", prod.getNombre())
					.addValue("inDescripcion", prod.getDescripcion())
					.addValue("inPrecio", prod.getPrecio());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(SetProducto)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inSKU", Types.VARCHAR),
					new SqlParameter("inNombre", Types.VARCHAR),
					new SqlParameter("inDescripcion", Types.VARCHAR),
					new SqlParameter("inPrecio", Types.INTEGER),
					new SqlOutParameter("outId", Types.INTEGER)
					);
					
			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId > 0)
				ret = ("Creado Correctamente con el Id: " + outId.toString());
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}
	
	public List<Producto> GetProducto(String SKU, String Nombre)
	{
		List<Producto> Pro = new ArrayList<Producto>();
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		RowMapper<Producto> mapConfig = null;
		try
		{
			mapConfig = (RS, rowNum) ->
			{
				Producto Pr = new Producto();
				
				Pr.setId(RS.getInt("id"));
				Pr.setSku(RS.getString("SKU"));
				Pr.setNombre(RS.getString("Nombre"));
				Pr.setDescripcion(RS.getString("Descripcion"));
				Pr.setPrecio(RS.getInt("Precio"));
				
				return Pr;
				
			};
			
			SqlParameterSource In = new MapSqlParameterSource()
					.addValue("inSKU", SKU)
					.addValue("inNombre", Nombre);
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(GetProducto)
					.returningResultSet(Cursor, mapConfig);
			
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inSKU", Types.VARCHAR),
					new SqlParameter("inNombre", Types.VARCHAR)
					);
			
			Map<String, Object> Out = stored.execute(In);
			
			if((List<Producto>) Out.get(Cursor) != null)
			{
				Pro = (List<Producto>) Out.get(Cursor);
				return Pro;
			}
		}
		catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return Pro;
	}
	
	public String UpdateProducto(Producto prod)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inSKU", prod.getSku())
					.addValue("inNombre", prod.getNombre())
					.addValue("inDescripcion", prod.getDescripcion())
					.addValue("inPrecio", prod.getPrecio());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(UpdateProducto)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inSKU", Types.VARCHAR),
					new SqlParameter("inNombre", Types.VARCHAR),
					new SqlParameter("inDescripcion", Types.VARCHAR),
					new SqlParameter("inPrecio", Types.INTEGER),
					new SqlOutParameter("outId", Types.INTEGER)
					);
					
			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId > 0)
				ret = "Se actualiz?? correctamente con el Id: " + outId;
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}

	public String DeleteProducto(Producto prod)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inSKU", prod.getSku());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(DeleteProducto)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inSKU", Types.VARCHAR),
					new SqlOutParameter("outId", Types.INTEGER)
					);
					
			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId == 0)
				ret = "Se elimin?? correctamente";
			else
				ret = "No se pudo eliminar el registro";
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}
	
	public String SetPlazo(plazo pla)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inPlazo", pla.getSemanas())
					.addValue("inTasaNor", pla.getTasaNormal())
					.addValue("inTasaPun", pla.getTasaPuntual());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(SetPlazo)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inPlazo", Types.INTEGER),
					new SqlParameter("inTasaNor", Types.DOUBLE),
					new SqlParameter("inTasaPun", Types.DOUBLE),
					new SqlOutParameter("outId", Types.INTEGER)
					);
					
			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId > 0)
				ret = "Se registr?? correctamente con el Id: " + outId;
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}
	
	public List<plazo> GetPlazo(plazo plaz)
	{
		List<plazo> Plazo = new ArrayList<plazo>();
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		RowMapper<plazo> mapConfig = null;
		
		try
		{
			mapConfig = (RS, rowNum) ->
			{
				plazo Pl = new plazo();
				
				Pl.setId(RS.getInt("id"));
				Pl.setSemanas(RS.getInt("Plazo_Semanal"));
				Pl.setTasaNormal(RS.getDouble("Tasa_Normal"));
				Pl.setTasaPuntual(RS.getDouble("Tasa_Puntual"));
				
				return Pl;
			};
			
			SqlParameterSource In = new MapSqlParameterSource()
					.addValue("inSemanas", plaz.getSemanas());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(GetPlazo)
					.returningResultSet(Cursor, mapConfig);
			
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inSemanas", Types.INTEGER)
					);
			
			Map<String, Object> Out = stored.execute(In);
			
			if((List<plazo>) Out.get(Cursor) != null)
			{
				Plazo = (List<plazo>) Out.get(Cursor);
				return Plazo;
			}
		}
		catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return Plazo;
	}
	
	public String UpdatePlazo(plazo pla)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inPlazo", pla.getSemanas())
					.addValue("inTasaNor", pla.getTasaNormal())
					.addValue("inTasaPun", pla.getTasaPuntual());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(UpdatePlazo)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inPlazo", Types.INTEGER),
					new SqlParameter("inTasaNor", Types.DOUBLE),
					new SqlParameter("inTasaPun", Types.DOUBLE),
					new SqlOutParameter("outId", Types.INTEGER)
					);

			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId > 0)
				ret = "Se actualiz?? correctamente el registro con el Id: " + outId;
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}

	public String DeletePlazo(plazo pla)
	{
		String ret = null;
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
		RowMapper<String> mapConfig = null;
		
		try
		{
			SqlParameterSource SPParametros = new MapSqlParameterSource()
					.addValue("inPlazo", pla.getSemanas());
			
			SimpleJdbcCall stored = new SimpleJdbcCall(this.jdbcTemplate)
					.withProcedureName(DeletePlazo)
					.returningResultSet(Cursor, mapConfig);
					
			stored.withoutProcedureColumnMetaDataAccess();
			stored.declareParameters(
					new SqlParameter("inPlazo", Types.INTEGER),
					new SqlOutParameter("outId", Types.INTEGER)
					);
					
			Map<String, Object> out = stored.execute(SPParametros);
			
			Integer outId = (Integer) out.get("outId");
			
			if (outId == 0)
				ret = "Se elimin?? correctamente";
			else
				ret = "No se pudo eliminar el registro";
			
		} catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		
		return ret;
	}
	
}
