import { Link } from "react-router-dom";
import { Instagram, Facebook, Mail, MapPin, Phone } from "lucide-react";

export default function Footer() {
  return (
    <footer className="bg-secondary border-t border-border">
      <div className="container py-12 grid grid-cols-1 md:grid-cols-4 gap-8">
        <div>
          <h3 className="font-display text-xl font-bold text-foreground mb-3">SAILOR</h3>
          <p className="text-sm text-muted-foreground">Más de 21 años vistiendo con elegancia y estilo.</p>
        </div>
        <div>
          <h4 className="text-sm font-semibold text-foreground mb-3 tracking-wider">TIENDA</h4>
          <div className="space-y-2 text-sm text-muted-foreground">
            <Link to="/catalogo" className="block hover:text-primary transition-colors">Catálogo</Link>
            <Link to="/colecciones" className="block hover:text-primary transition-colors">Colecciones</Link>
            <Link to="/ofertas" className="block hover:text-primary transition-colors">Ofertas</Link>
          </div>
        </div>
        <div>
          <h4 className="text-sm font-semibold text-foreground mb-3 tracking-wider">EMPRESA</h4>
          <div className="space-y-2 text-sm text-muted-foreground">
            <Link to="/nosotros" className="block hover:text-primary transition-colors">Nosotros</Link>
            <Link to="/contacto" className="block hover:text-primary transition-colors">Contacto</Link>
          </div>
        </div>
        <div>
          <h4 className="text-sm font-semibold text-foreground mb-3 tracking-wider">CONTACTO</h4>
          <div className="space-y-2 text-sm text-muted-foreground">
            <div className="flex items-center gap-2"><Phone className="h-4 w-4" /> WhatsApp</div>
            <div className="flex items-center gap-2"><Mail className="h-4 w-4" /> info@sailor.com</div>
            <div className="flex items-center gap-2"><MapPin className="h-4 w-4" /> Colombia</div>
          </div>
          <div className="flex gap-3 mt-4">
            <a href="https://instagram.com" target="_blank" rel="noreferrer" className="text-muted-foreground hover:text-primary transition-colors"><Instagram className="h-5 w-5" /></a>
            <a href="https://facebook.com" target="_blank" rel="noreferrer" className="text-muted-foreground hover:text-primary transition-colors"><Facebook className="h-5 w-5" /></a>
          </div>
        </div>
      </div>
      <div className="border-t border-border py-4 text-center text-xs text-muted-foreground">
        © 2026 SAILOR S.A.S. Todos los derechos reservados.
      </div>
    </footer>
  );
}
