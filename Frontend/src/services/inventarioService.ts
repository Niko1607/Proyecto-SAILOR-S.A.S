import { getAuthHeaders } from "@/lib/utils";

const API_URL = "https://proyecto-sailor-sas-production.up.railway.app/api/inventario";

export type ProductoInventario = {
  id: number;
  activo: boolean;
  categoria: string;
  descripcion: string;
  fechaRegistro: string;
  imagen: string;
  nombreProducto: string;
  precioProducto: number;
  stock: number;
  stockMaximo: number;
  stockMinimo: number;
};

const jsonHeaders = () => getAuthHeaders();

export const getInventario = async () => {
  const response = await fetch(API_URL, {
    headers: jsonHeaders(),
  });

  if (!response.ok) {
    throw new Error("Error al obtener inventario");
  }

  return response.json();
};

export const getInventarioById = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`, {
    headers: jsonHeaders(),
  });
  return response.json();
};

export const crearInventario = async (inventario: ProductoInventario) => {
  const response = await fetch(`${API_URL}/movimiento`, {
    method: "POST",
    headers: jsonHeaders(),
    body: JSON.stringify(inventario),
  });

  return response.json();
};

export const actualizarInventario = async (id: number, inventario: ProductoInventario) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: jsonHeaders(),
    body: JSON.stringify(inventario),
  });

  return response.json();
};

export const eliminarInventario = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
    headers: jsonHeaders(),
  });
};