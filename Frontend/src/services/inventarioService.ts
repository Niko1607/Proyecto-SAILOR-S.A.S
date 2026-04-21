const API_URL = "http://localhost:8081/api/inventario";

export type Inventario = {
  id?: number
  stock: number
  stockMinimo: number
  stockMaximo: number
  producto: {
    id: number
  }
};

export const getInventario = async () => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener inventario");
  }

  return response.json();
};

export const getInventarioById = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`);
  return response.json();
};

export const crearInventario = async (inventario: Inventario) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(inventario),
  });

  return response.json();
};

export const actualizarInventario = async (id: number, inventario: Inventario) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(inventario),
  });

  return response.json();
};

export const eliminarInventario = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
};