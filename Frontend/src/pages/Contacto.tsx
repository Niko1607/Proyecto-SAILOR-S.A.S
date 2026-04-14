import { motion } from "framer-motion";
import { Mail, MapPin, Phone, MessageCircle } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";

export default function Contacto() {
  return (
    <div className="py-16">
      <div className="container">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="text-center mb-12">
          <p className="text-primary text-sm tracking-[0.3em] font-semibold uppercase mb-3">Hablemos</p>
          <h1 className="font-display text-4xl md:text-5xl font-bold text-foreground">Contacto</h1>
        </motion.div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
          <div className="space-y-6">
            <div className="bg-card border border-border rounded-lg p-6 flex items-start gap-4">
              <MessageCircle className="h-6 w-6 text-primary mt-1" />
              <div>
                <h3 className="font-semibold text-foreground">WhatsApp</h3>
                <p className="text-muted-foreground text-sm">+57 300 123 4567</p>
              </div>
            </div>
            <div className="bg-card border border-border rounded-lg p-6 flex items-start gap-4">
              <Mail className="h-6 w-6 text-primary mt-1" />
              <div>
                <h3 className="font-semibold text-foreground">Correo</h3>
                <p className="text-muted-foreground text-sm">info@sailor.com</p>
              </div>
            </div>
            <div className="bg-card border border-border rounded-lg p-6 flex items-start gap-4">
              <MapPin className="h-6 w-6 text-primary mt-1" />
              <div>
                <h3 className="font-semibold text-foreground">Dirección</h3>
                <p className="text-muted-foreground text-sm">Bogotá, Colombia</p>
              </div>
            </div>
          </div>

          <form className="space-y-4">
            <Input placeholder="Tu nombre" className="bg-card border-border" />
            <Input placeholder="Tu correo" type="email" className="bg-card border-border" />
            <Textarea placeholder="Tu mensaje" className="bg-card border-border min-h-[120px]" />
            <Button variant="heroFilled" size="lg" className="w-full">Enviar Mensaje</Button>
          </form>
        </div>
      </div>
    </div>
  );
}
