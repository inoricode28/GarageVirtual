import { Router } from "express";
import { methods as vehiculoController } from "./../controller/vehiculo.controller";

const router = Router();

router.get("/", vehiculoController.getVehiculos); // Obtener todos los vehículos
router.get("/:id", vehiculoController.getVehiculo); // Obtener un vehículo por ID
router.post("/", vehiculoController.addVehiculo); // Agregar un nuevo vehículo
router.put("/:id", vehiculoController.updateVehiculo); // Actualizar un vehículo por ID
router.delete("/:id", vehiculoController.deleteVehiculo); // Eliminar un vehículo por ID

// Nueva ruta para obtener un vehículo por su placa
router.get("/placa/:placa", vehiculoController.getVehiculoByPlaca); // Obtener un vehículo por Placa

export default router;
