package com.credito.ba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credito.ba.VO.Cotizacion;
import com.credito.ba.VO.Producto;
import com.credito.ba.VO.plazo;
import com.credito.ba.service.creditoService;

@ControllerAdvice
@RestController
@RequestMapping("Credito")
public class creditoController
{
	@Autowired
	creditoService CreService;
	
	@PostMapping(value = "/SetProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String SetProducto(@RequestBody Producto prod)
	{
		return CreService.SetProducto(prod);
	}
	
	@PostMapping(value = "/GetProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> GetProducto(@RequestBody Producto prod)
	{
		return CreService.GetProducto(prod.getSku(), prod.getNombre());
	}
	
	@PostMapping(value = "/UpdateProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String UpdateProducto(@RequestBody Producto prod)
	{
		return CreService.UpdateProducto(prod);
	}
	
	@PostMapping(value = "/DeleteProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String DeleteProducto(@RequestBody Producto prod)
	{
		return CreService.DeleteProducto(prod);
	}
	
	@PostMapping(value = "/SetPlazo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String SetPlazo(@RequestBody plazo pla)
	{
		return CreService.SetPlazo(pla);
	}
	
	@PostMapping(value = "/GetPlazo", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<plazo> GetPlazo(@RequestBody plazo pla)
	{
		return CreService.GetPlazo(pla);
	}
	
	@PostMapping(value = "/UpdatePlazo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String UpdatePlazo(@RequestBody plazo pla)
	{
		return CreService.UpdatePlazo(pla);
	}
	
	@PostMapping(value = "/DeletePlazo", produces = MediaType.APPLICATION_JSON_VALUE)
	public String DeletePlazo(@RequestBody plazo pla)
	{
		return CreService.DeletePlazo(pla);
	}
	
	@PostMapping(value = "/GetCotizacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cotizacion GetCotizacion(@RequestBody Cotizacion cot)
	{
		return CreService.GetCotizacion(cot);
	}
	
	@PostMapping(value = "/Multiplicacion", produces = MediaType.APPLICATION_JSON_VALUE)
	public int MultiSinSigno(@RequestParam("a")int A, @RequestParam("b") int B)
	{
		return CreService.MultiSinSigno(A, B);
	}
}
