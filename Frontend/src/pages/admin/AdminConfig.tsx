import { motion } from "framer-motion";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Image, Truck, FileText } from "lucide-react";

export default function AdminConfig() {
  return (
    <div>
      <h1 className="font-display text-2xl font-bold text-foreground mb-6">Configuración</h1>

      <div className="space-y-6">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} className="bg-card border border-border rounded-lg p-6">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <Image className="h-5 w-5 text-primary" /> Banners de Inicio
          </h2>
          <p className="text-muted-foreground text-sm mb-4">Configura las imágenes y textos del banner principal de la tienda.</p>
          <div className="space-y-3">
            <Input placeholder="Título del banner" className="bg-secondary border-border" defaultValue="Ropa que transforma tu estilo" />
            <Textarea placeholder="Descripción" className="bg-secondary border-border" defaultValue="Diseño, calidad y confort en cada fibra." />
            <Button variant="heroFilled">Guardar Cambios</Button>
          </div>
        </motion.div>

        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.1 }} className="bg-card border border-border rounded-lg p-6">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <Truck className="h-5 w-5 text-primary" /> Envíos
          </h2>
          <div className="space-y-3">
            <Input placeholder="Costo de envío estándar" className="bg-secondary border-border" defaultValue="$12,000" />
            <Input placeholder="Envío gratis a partir de" className="bg-secondary border-border" defaultValue="$200,000" />
            <Button variant="heroFilled">Guardar</Button>
          </div>
        </motion.div>

        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }} transition={{ delay: 0.2 }} className="bg-card border border-border rounded-lg p-6">
          <h2 className="font-display text-lg font-bold text-foreground flex items-center gap-2 mb-4">
            <FileText className="h-5 w-5 text-primary" /> Políticas
          </h2>
          <div className="space-y-3">
            <Textarea placeholder="Política de devoluciones" className="bg-secondary border-border min-h-[100px]" defaultValue="Aceptamos devoluciones dentro de los 15 días siguientes a la compra." />
            <Button variant="heroFilled">Guardar</Button>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
