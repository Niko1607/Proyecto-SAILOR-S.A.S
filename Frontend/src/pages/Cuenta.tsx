import { motion } from "framer-motion";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Lock, Mail, LogIn, Briefcase, Shield, User } from "lucide-react";

type Role = "empleado" | "administrador" | "cliente";
type View = "login" | "register";

const roles: { key: Role; label: string; desc: string; icon: React.ReactNode }[] = [
  { key: "empleado", label: "Empleado", desc: "Acceso de personal", icon: <Briefcase className="h-5 w-5" /> },
  { key: "administrador", label: "Administrador", desc: "Control total", icon: <Shield className="h-5 w-5" /> },
  { key: "cliente", label: "Cliente", desc: "Portal de cliente", icon: <User className="h-5 w-5" /> },
];

export default function Cuenta() {
  const navigate = useNavigate();
  const [view, setView] = useState<View>("login");
  const [selectedRole, setSelectedRole] = useState<Role>("empleado");

  return (
    <div className="py-16 flex items-center justify-center min-h-[70vh]">
      <div className="container max-w-md">
        <motion.div initial={{ opacity: 0, y: 20 }} animate={{ opacity: 1, y: 0 }}>
          {view === "login" ? (
            /* ——— LOGIN ——— */
            <div className="bg-card border-2 border-border rounded-2xl p-8">
              <div className="text-center mb-6">
                <div className="mx-auto w-12 h-12 rounded-xl bg-primary/20 flex items-center justify-center mb-4">
                  <LogIn className="h-6 w-6 text-primary" />
                </div>
                <h1 className="font-display text-2xl font-bold text-foreground">Iniciar Sesión</h1>
                <p className="text-muted-foreground text-sm mt-1">Selecciona tu rol y accede al sistema</p>
              </div>

              {/* Role selector */}
              <div className="grid grid-cols-3 gap-3 mb-6">
                {roles.map((r) => (
                  <button
                    key={r.key}
                    onClick={() => setSelectedRole(r.key)}
                    className={`flex flex-col items-center gap-1.5 p-3 rounded-xl border-2 transition-all text-center ${
                      selectedRole === r.key
                        ? "border-primary bg-primary/10 text-primary"
                        : "border-border bg-secondary/40 text-muted-foreground hover:border-muted-foreground/50"
                    }`}
                  >
                    {r.icon}
                    <span className="text-xs font-semibold">{r.label}</span>
                    <span className="text-[10px] leading-tight opacity-70">{r.desc}</span>
                  </button>
                ))}
              </div>

              <form className="space-y-4" onSubmit={(e) => {
                e.preventDefault();
                if (selectedRole === "administrador") navigate("/admin");
                else if (selectedRole === "empleado") navigate("/empleado");
                else navigate("/");
              }}>
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Correo electrónico</label>
                  <div className="relative">
                    <Mail className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input placeholder="correo@ejemplo.com" type="email" className="bg-secondary border-border pl-10" />
                  </div>
                </div>
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Contraseña</label>
                  <div className="relative">
                    <Lock className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input placeholder="••••••••" type="password" className="bg-secondary border-border pl-10" />
                  </div>
                </div>
                <Button type="submit" variant="heroFilled" size="lg" className="w-full gap-2">
                  <LogIn className="h-4 w-4" />
                  Ingresar como {roles.find((r) => r.key === selectedRole)?.label}
                </Button>
              </form>

              <div className="mt-5 text-center space-y-2">
                <button className="text-sm text-muted-foreground hover:text-primary transition-colors">
                  ¿Olvidaste tu contraseña?
                </button>
                {selectedRole === "cliente" && (
                  <div>
                    <button
                      onClick={() => setView("register")}
                      className="text-sm text-muted-foreground hover:text-primary transition-colors"
                    >
                      ¿Eres cliente nuevo? <span className="text-primary font-medium">Regístrate aquí</span>
                    </button>
                  </div>
                )}
              </div>
            </div>
          ) : (
            /* ——— REGISTER (solo clientes) ——— */
            <div className="bg-card border-2 border-border rounded-2xl p-8">
              <div className="text-center mb-6">
                <div className="mx-auto w-12 h-12 rounded-xl bg-primary/20 flex items-center justify-center mb-4">
                  <User className="h-6 w-6 text-primary" />
                </div>
                <h1 className="font-display text-2xl font-bold text-foreground">Registro de Cliente</h1>
                <p className="text-muted-foreground text-sm mt-1">Crea tu cuenta para realizar pedidos</p>
              </div>

              <form className="space-y-4">
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Nombre completo</label>
                  <div className="relative">
                    <User className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input placeholder="Tu nombre" className="bg-secondary border-border pl-10" />
                  </div>
                </div>
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Correo electrónico</label>
                  <div className="relative">
                    <Mail className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input placeholder="correo@ejemplo.com" type="email" className="bg-secondary border-border pl-10" />
                  </div>
                </div>
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Teléfono</label>
                  <Input placeholder="+57 300 000 0000" type="tel" className="bg-secondary border-border" />
                </div>
                <div>
                  <label className="text-sm font-medium text-foreground mb-1.5 block">Contraseña</label>
                  <div className="relative">
                    <Lock className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
                    <Input placeholder="••••••••" type="password" className="bg-secondary border-border pl-10" />
                  </div>
                </div>
                <Button variant="heroFilled" size="lg" className="w-full gap-2">
                  <User className="h-4 w-4" />
                  Crear cuenta de Cliente
                </Button>
              </form>

              <div className="mt-5 text-center">
                <button
                  onClick={() => setView("login")}
                  className="text-sm text-muted-foreground hover:text-primary transition-colors"
                >
                  ¿Ya tienes cuenta? <span className="text-primary font-medium">Inicia sesión</span>
                </button>
              </div>
            </div>
          )}
        </motion.div>
      </div>
    </div>
  );
}
