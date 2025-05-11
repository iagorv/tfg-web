
export function esMayorDe16(fechaStr) {
    if (!fechaStr) return false;
    const fechaNacimiento = new Date(fechaStr);
    const hoy = new Date();
    const edad = hoy.getFullYear() - fechaNacimiento.getFullYear();
    const mes = hoy.getMonth() - fechaNacimiento.getMonth();
    const dia = hoy.getDate() - fechaNacimiento.getDate();

    return edad > 16 || (edad === 16 && (mes > 0 || (mes === 0 && dia >= 0)));
}
