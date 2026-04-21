const API_URL = "http://localhost:8081/api/proveedores";

export type Proveedor = {
  id?: number
  nombre: string
  telefono: string
  direccion: string
};

export const getProveedores = async () => {
  const response = await fetch(API_URL);

  if (!response.ok) {
    throw new Error("Error al obtener proveedores");
  }

  return response.json();
};

export const getProveedorById = async (id: number) => {
  const response = await fetch(`${API_URL}/${id}`);
  return response.json();
};

export const crearProveedor = async (proveedor: Proveedor) => {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(proveedor),
  });

  return response.json();
};

export const actualizarProveedor = async (id: number, proveedor: Proveedor) => {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(proveedor),
  });

  return response.json();
};

export const eliminarProveedor = async (id: number) => {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
};