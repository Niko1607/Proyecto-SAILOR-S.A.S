import { Link, Outlet, useLocation } from "react-router-dom";
import { LayoutDashboard, Package, ShoppingBag, Users, BarChart3, Settings, Store } from "lucide-react";

const sidebarLinks = [
  { label: "Dashboard", path: "/admin", icon: LayoutDashboard },
  { label: "Inventario", path: "/admin/inventario", icon: Package },
  { label: "Pedidos", path: "/admin/pedidos", icon: ShoppingBag },
  { label: "Clientes", path: "/admin/clientes", icon: Users },
  { label: "Reportes", path: "/admin/reportes", icon: BarChart3 },
  { label: "Configuración", path: "/admin/config", icon: Settings },
];

export default function AdminLayout() {
  const location = useLocation();

  return (
    <div className="flex min-h-screen">
      <aside className="w-64 bg-sidebar border-r border-sidebar-border flex-shrink-0">
        <div className="p-4 border-b border-sidebar-border">
          <Link to="/" className="flex items-center gap-2 text-sidebar-foreground hover:text-sidebar-primary transition-colors">
            <Store className="h-5 w-5" />
            <span className="font-display font-bold tracking-wider">SAILOR ADMIN</span>
          </Link>
        </div>
        <nav className="p-3 space-y-1">
          {sidebarLinks.map((l) => {
            const isActive = location.pathname === l.path;
            return (
              <Link
                key={l.path}
                to={l.path}
                className={`flex items-center gap-3 px-3 py-2.5 rounded-md text-sm font-medium transition-colors ${
                  isActive
                    ? "bg-sidebar-accent text-sidebar-primary"
                    : "text-sidebar-foreground hover:bg-sidebar-accent hover:text-sidebar-accent-foreground"
                }`}
              >
                <l.icon className="h-4 w-4" />
                {l.label}
              </Link>
            );
          })}
        </nav>
      </aside>
      <main className="flex-1 bg-background p-6 overflow-auto">
        <Outlet />
      </main>
    </div>
  );
}
