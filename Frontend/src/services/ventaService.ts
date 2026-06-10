import { getAuthHeaders } from "@/lib/utils";

const API_URL = "https://proyecto-sailor-sas-production.up.railway.app/api/ventas";

export type Venta = {
  id?: number
  fecha?: string
  estado?: boolean
  total: number
  usuario?: {
    id: number
  }
} 

export const getVentas = async () => {
  const response = await fetch(API_URL, {
    headers: getAuthHeaders(),
  });

  if (!response.ok) {
    throw new Error(`Error al obtener ventas (${response.status})`);
  }

  return response.json();
};

export const getVentaById = async (id: number) => {
    const response = await fetch(`${API_URL}/${id}`, {
      headers: getAuthHeaders(),
    });
    
    if(!response.ok) {
      throw new Error(`Error al obtener venta (${response.status})`);
    }

    return response.json();
};

export const crearventa = async (venta: Venta) => {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: getAuthHeaders(),
      body: JSON.stringify(venta),
    });

    return response.json();
};

export const eliminarVenta = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
    headers: getAuthHeaders(),
  });
};

export const actualizarVenta = async (id: number, venta: Venta) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: getAuthHeaders(),
    body: JSON.stringify(venta),
  });

  return response.json();
};

