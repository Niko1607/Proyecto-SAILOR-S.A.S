import { useState } from "react";
import { Link, useLocation } from "react-router-dom";
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

export default function Navbar() {
  const [mobileOpen, setMobileOpen] = useState(false);
  const location = useLocation();
  const { totalItems, setIsCartOpen } = useCart();

  return (
    <>
      {/* Top bar */}
      <div className="bg-secondary/60 text-muted-foreground text-xs py-1.5 px-4 flex justify-between items-center">
        <span>ENVÍOS A TODO EL PAÍS · +21 AÑOS DE EXPERIENCIA</span>
        <div className="flex gap-4">
          <a href="https://instagram.com" target="_blank" rel="noreferrer" className="hover:text-foreground transition-colors">Instagram</a>
          <a href="https://facebook.com" target="_blank" rel="noreferrer" className="hover:text-foreground transition-colors">Facebook</a>
        </div>
      </div>

      {/* Main nav */}
      <nav className="bg-background/95 backdrop-blur-md border-b border-border sticky top-0 z-50">
        <div className="container flex items-center justify-between h-16">
          <Link to="/" className="flex items-center gap-2">
            <img src={sailorLogo} alt="Sailor" className="h-10 w-10" />
            <div>
              <span className="font-display text-lg font-bold tracking-wider text-foreground">SAILOR</span>
              <span className="block text-[10px] text-muted-foreground tracking-widest">S.A.S</span>
            </div>
          </Link>

          {/* Desktop links */}
          <div className="hidden lg:flex items-center gap-6">
            {navLinks.map((l) => (
              <Link
                key={l.path}
                to={l.path}
                className={`text-xs tracking-wider font-medium transition-colors hover:text-primary ${
                  location.pathname === l.path ? "text-primary" : "text-muted-foreground"
                }`}
              >
                {l.label}
              </Link>
            ))}
          </div>

          <div className="flex items-center gap-2">
            <Link to="/cuenta">
              <Button variant="ghost" size="icon"><User className="h-5 w-5" /></Button>
            </Link>
            <Button variant="ghost" size="icon" className="relative" onClick={() => setIsCartOpen(true)}>
              <ShoppingCart className="h-5 w-5" />
              {totalItems > 0 && (
                <span className="absolute -top-1 -right-1 w-5 h-5 rounded-full bg-primary text-primary-foreground text-[10px] font-bold flex items-center justify-center">
                  {totalItems}
                </span>
              )}
            </Button>
            <Button variant="ghost" size="icon" className="lg:hidden" onClick={() => setMobileOpen(!mobileOpen)}>
              {mobileOpen ? <X className="h-5 w-5" /> : <Menu className="h-5 w-5" />}
            </Button>
          </div>
        </div>

        {/* Mobile menu */}
        {mobileOpen && (
          <div className="lg:hidden bg-background border-t border-border py-4 px-6 space-y-3">
            {navLinks.map((l) => (
              <Link
                key={l.path}
                to={l.path}
                onClick={() => setMobileOpen(false)}
                className={`block text-sm tracking-wider font-medium transition-colors hover:text-primary ${
                  location.pathname === l.path ? "text-primary" : "text-muted-foreground"
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
