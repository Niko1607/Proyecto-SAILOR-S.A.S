import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { Toaster } from "@/components/ui/toaster";
import { TooltipProvider } from "@/components/ui/tooltip";
import { CartProvider } from "@/context/CartContext";
import CartDrawer from "@/components/store/CartDrawer";

import StoreLayout from "@/components/store/StoreLayout";
import AdminLayout from "@/components/admin/AdminLayout";

import Index from "./pages/Index";
import Nosotros from "./pages/Nosotros";
import Catalogo from "./pages/Catalogo";
import Colecciones from "./pages/Colecciones";
import Ofertas from "./pages/Ofertas";
import Pedidos from "./pages/Pedidos";
import Contacto from "./pages/Contacto";
import Cuenta from "./pages/Cuenta";
import ProductoDetalle from "./pages/ProductoDetalle";

import AdminDashboard from "./pages/admin/AdminDashboard";
import AdminInventario from "./pages/admin/AdminInventario";
import AdminPedidos from "./pages/admin/AdminPedidos";
import AdminClientes from "./pages/admin/AdminClientes";
import AdminReportes from "./pages/admin/AdminReportes";
import AdminConfig from "./pages/admin/AdminConfig";

import EmpleadoLayout from "@/components/empleado/EmpleadoLayout";
import EmpleadoPedidos from "./pages/empleado/EmpleadoPedidos";
import EmpleadoInventario from "./pages/empleado/EmpleadoInventario";
import EmpleadoClientes from "./pages/empleado/EmpleadoClientes";

import AdminProductos from "./pages/admin/AdminProductos";

import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <CartProvider>
        <Toaster />
        <Sonner />
        <CartDrawer />
        <BrowserRouter>
          <Routes>
            {/* Store routes */}
            <Route element={<StoreLayout />}>
              <Route path="/" element={<Index />} />
              <Route path="/nosotros" element={<Nosotros />} />
              <Route path="/catalogo" element={<Catalogo />} />
              <Route path="/colecciones" element={<Colecciones />} />
              <Route path="/ofertas" element={<Ofertas />} />
              <Route path="/pedidos" element={<Pedidos />} />
              <Route path="/contacto" element={<Contacto />} />
              <Route path="/cuenta" element={<Cuenta />} />
              <Route path="/producto/:id" element={<ProductoDetalle />} />
            </Route>

            {/* Admin routes */}
            <Route path="/admin" element={<AdminLayout />}>
              <Route index element={<AdminDashboard />} />
              <Route path="inventario" element={<AdminInventario />} />
              <Route path="pedidos" element={<AdminPedidos />} />
              <Route path="clientes" element={<AdminClientes />} />
              <Route path="reportes" element={<AdminReportes />} />
              <Route path="config" element={<AdminConfig />} />
              <Route path="productos" element={<AdminProductos />} />
            </Route>

            {/* Employee routes */}
            <Route path="/empleado" element={<EmpleadoLayout />}>
              <Route index element={<EmpleadoPedidos />} />
              <Route path="inventario" element={<EmpleadoInventario />} />
              <Route path="clientes" element={<EmpleadoClientes />} />
            </Route>

            <Route path="*" element={<NotFound />} />
          </Routes>
        </BrowserRouter>
      </CartProvider>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
