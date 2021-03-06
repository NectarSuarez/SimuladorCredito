package com.credito.ba.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credito.ba.VO.Cotizacion;
import com.credito.ba.VO.Producto;
import com.credito.ba.VO.plazo;
import com.credito.ba.DAO.creditoDAO;

@Service
public class creditoService
{

	@Autowired
	creditoDAO CreDAO;

	public String SetProducto(Producto prod)
	{
		return CreDAO.SetProducto(prod);
	}
	
	public List<Producto> GetProducto(String SKU, String Nombre)
	{
		return CreDAO.GetProducto(SKU, Nombre);
	}
	
	public String UpdateProducto(Producto prod)
	{
		return CreDAO.UpdateProducto(prod);
	}

	public String DeleteProducto(Producto prod)
	{
		return CreDAO.DeleteProducto(prod);
	}
	
	public String SetPlazo(plazo Pla)
	{
		return CreDAO.SetPlazo(Pla);
	}

	public List<plazo> GetPlazo(plazo pla)
	{
		return CreDAO.GetPlazo(pla);
	}
	
	public String UpdatePlazo(plazo pla)
	{
		return CreDAO.UpdatePlazo(pla);
	}

	public String DeletePlazo(plazo pla)
	{
		return CreDAO.DeletePlazo(pla);
	}	
	
	public Cotizacion GetCotizacion(Cotizacion cot)
	{
		Cotizacion Cot = new Cotizacion();
		double AbonoNor;
		double AbonoPun;

		// Operación para Abono "Normal"
		AbonoNor = (((cot.getPrecio() * cot.getTasaNormal()) + cot.getPrecio()) / cot.getPlazo());
		
		// Operación para Abono "Puntual"
		AbonoPun = (((cot.getPrecio() * cot.getTasaPuntual()) + cot.getPrecio()) / cot.getPlazo());
		
		Cot.setPrecio(cot.getPrecio());
		Cot.setPlazo(cot.getPlazo());
		Cot.setTasaNormal(cot.getTasaNormal());
		Cot.setTasaPuntual(cot.getTasaPuntual());
		Cot.setAbonoNormal(AbonoNor);
		Cot.setAbonoPuntual(AbonoPun);
		
		return Cot;
	}
	
	public int MultiSinSigno(int A, int B)
	{
		int Res = 0;
		
		for (int i = 0; i < B; i++)
		{
			Res = Res + A;
		}
		
		return Res;
	}
}
