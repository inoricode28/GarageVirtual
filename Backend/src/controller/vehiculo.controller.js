// vehiculo.controller.js
import { getConnection } from "./../database/conexcion";

const getVehiculos = async (req, res) => {
    try {
        const connection = await getConnection();
        const [rows] = await connection.query("SELECT id, Placa, Marca, Modelo, Kilometraje FROM Vehiculo");
        console.log(rows); // Solo los datos de los vehículos
        res.json(rows); // Envía solo los datos de los vehículos al cliente
    } catch (error) {
        res.status(500).send(error.message);
    }
};

const getVehiculo = async (req, res) => {
    try {
        const { id } = req.params;
        const connection = await getConnection();
        const [rows] = await connection.query("SELECT id, Placa, Marca, Modelo, Kilometraje FROM Vehiculo WHERE id = ?", [id]);
        console.log(rows); // Solo los datos del vehículo solicitado
        res.json(rows); // Envía solo los datos del vehículo solicitado al cliente
    } catch (error) {
        res.status(500).send(error.message);
    }
};

const addVehiculo = async (req, res) => {
    try {
        const { Placa, Marca, Modelo, Kilometraje } = req.body;

        if (Placa === undefined || Marca === undefined || Modelo === undefined || Kilometraje === undefined) {
            res.status(400).json({ message: "Bad Request. Please fill all fields." });
            return;
        }

        const vehiculo = { Placa, Marca, Modelo, Kilometraje };
        const connection = await getConnection();
        await connection.query("INSERT INTO Vehiculo SET ?", vehiculo);
        res.json({ rpta: true, mensaje: "El vehículo fue registrado correctamente." });
    } catch (error) {
        res.status(500).send(error.message);
    }
};

const updateVehiculo = async (req, res) => {
    try {
        const { id } = req.params;
        const { Placa, Marca, Modelo, Kilometraje } = req.body;

        if (Placa === undefined || Marca === undefined || Modelo === undefined || Kilometraje === undefined) {
            res.status(400).json({ message: "Bad Request. Please fill all fields." });
            return;
        }

        const vehiculo = { Placa, Marca, Modelo, Kilometraje };
        const connection = await getConnection();
        const result = await connection.query("UPDATE Vehiculo SET ? WHERE id = ?", [vehiculo, id]);
        console.log(result);
        res.json(result);
    } catch (error) {
        res.status(500).send(error.message);
    }
};

const deleteVehiculo = async (req, res) => {
    try {
        const { id } = req.params;
        const connection = await getConnection();
        const result = await connection.query("DELETE FROM Vehiculo WHERE id = ?", [id]);
        console.log(result);
        res.json(result);
    } catch (error) {
        res.status(500).send(error.message);
    }
};

export const methods = {
    getVehiculos,
    getVehiculo,
    addVehiculo,
    updateVehiculo,
    deleteVehiculo
};