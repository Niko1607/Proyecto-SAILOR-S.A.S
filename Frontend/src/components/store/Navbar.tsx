import { useState, useEffect } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { ShoppingCart, User, Menu, X } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useCart } from "@/context/CartContext";
import sailorLogo from "@/assets/sailor-logo.png";

const navLinks = [
  { label: "INICIO", path: "/" },
  { label: "NOSOTROS", path: "/nosotros" },
  { label: "CATÁLOGO", path: "/catalogo" },
  { label: "COLECCIONES", path: "/colecciones" },
  { label: "OFERTAS", path: "/ofertas" },
  { label: "PEDIDOS", path: "/pedidos" },
  { label: "CONTACTO", path: "/contacto" },
];

type Usuario = {
  id: number;
  nombre: string;
  apellido: string;
  correo: string;
  password: string;
  rol: string;
  direccion: string;
};

export default function Navbar() {

  const [mobileOpen, setMobileOpen] = useState(false);
  const location = useLocation();
  const navigate = useNavigate();
  const { totalItems, setIsCartOpen } = useCart();

  const [user, setUser] = useState<Usuario | null>(null);

  // Obtener usuario de sesión
  useEffect(() => {
    const usuarioGuardado = localStorage.getItem("usuario");

    if (usuarioGuardado) {
      setUser(JSON.parse(usuarioGuardado));
    }
  }, []);

  // Cerrar sesión
  const cerrarSesion = () => {
    localStorage.removeItem("usuario");
    navigate("/");
    window.location.reload();
  };

  return (
    <>
      {/* Top bar */}
      <div className="bg-secondary/60 text-muted-foreground text-xs py-1.5 px-4 flex justify-between items-center">
        <span>ENVÍOS A TODO EL PAÍS · +21 AÑOS DE EXPERIENCIA</span>

        <div className="flex gap-4">
          <a
            href="https://instagram.com"
            target="_blank"
            rel="noreferrer"
            className="hover:text-foreground transition-colors"
          >
            Instagram
          </a>

          <a
            href="https://facebook.com"
            target="_blank"
            rel="noreferrer"
            className="hover:text-foreground transition-colors"
          >
            Facebook
          </a>
        </div>
      </div>

      {/* Navbar */}
      <nav className="bg-background/95 backdrop-blur-md border-b border-border sticky top-0 z-50">
        <div className="container flex items-center justify-between h-16">

          {/* Logo */}
          <Link to="/" className="flex items-center gap-2">
            <img src={sailorLogo} alt="Sailor" className="h-10 w-10" />

            <div>
              <span className="font-display text-lg font-bold tracking-wider text-foreground">
                SAILOR
              </span>

              <span className="block text-[10px] text-muted-foreground tracking-widest">
                S.A.S
              </span>
            </div>
          </Link>

          {/* Links escritorio */}
          <div className="hidden lg:flex items-center gap-6">
            {navLinks.map((l) => (
              <Link
                key={l.path}
                to={l.path}
                className={`text-xs tracking-wider font-medium transition-colors hover:text-primary ${
                  location.pathname === l.path
                    ? "text-primary"
                    : "text-muted-foreground"
                }`}
              >
                {l.label}
              </Link>
            ))}
          </div>

          {/* Acciones */}
          <div className="flex items-center gap-2">

            {/* Usuario */}
            {user ? (
              <div className="flex items-center gap-2">

                <span className="text-xs font-medium hidden md:block">
                  👤 {user.nombre} ({user.rol})
                </span>

                <Button
                  variant="ghost"
                  size="sm"
                  onClick={cerrarSesion}
                  className="text-xs"
                >
                  Salir
                </Button>

              </div>
            ) : (
              <Link to="/cuenta">
                <Button variant="ghost" size="icon">
                  <User className="h-5 w-5" />
                </Button>
              </Link>
            )}

            {/* Carrito - Ahora con mejor visibility */}
            <Button
              variant="outline"
              size="icon"
              className="relative border-primary/20 hover:border-primary hover:bg-primary/5 transition-all"
              onClick={() => setIsCartOpen(true)}
              title="Abrir carrito de compras"
            >
              <ShoppingCart className="h-5 w-5 text-primary" />

              {totalItems > 0 && (
                <span className="absolute -top-2 -right-2 w-6 h-6 rounded-full bg-destructive text-white text-[11px] font-bold flex items-center justify-center animate-pulse">
                  {totalItems > 99 ? '99+' : totalItems}
                </span>
              )}
            </Button>

            {/* Menú móvil */}
            <Button
              variant="ghost"
              size="icon"
              className="lg:hidden"
              onClick={() => setMobileOpen(!mobileOpen)}
            >
              {mobileOpen ? (
                <X className="h-5 w-5" />
              ) : (
                <Menu className="h-5 w-5" />
              )}
            </Button>

          </div>
        </div>

        {/* Menú móvil */}
        {mobileOpen && (
          <div className="lg:hidden bg-background border-t border-border py-4 px-6 space-y-3">
            {navLinks.map((l) => (
              <Link
                key={l.path}
                to={l.path}
                onClick={() => setMobileOpen(false)}
                className={`block text-sm tracking-wider font-medium transition-colors hover:text-primary ${
                  location.pathname === l.path
                    ? "text-primary"
                    : "text-muted-foreground"
                }`}
              >
                {l.label}
              </Link>
            ))}
          </div>
        )}
      </nav>
    </>
  );
}