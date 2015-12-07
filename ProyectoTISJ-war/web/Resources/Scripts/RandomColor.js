function getColor(opacidad, indice) {
    colores = [
        "rgba(244, 67, 54,"+opacidad+")",
        "rgba(233, 30, 99,"+opacidad+")",
        "rgba(156, 39, 176,"+opacidad+")",
        "rgba(103, 58, 183,"+opacidad+")",
        "rgba(63, 81, 181,"+opacidad+")",
        "rgba(33, 150, 243,"+opacidad+")",
        "rgba(3, 169, 244,"+opacidad+")",
        "rgba(0, 188, 212,"+opacidad+")",
        "rgba(0, 150, 136,"+opacidad+")",
        "rgba(76, 175, 80,"+opacidad+")",
        "rgba(139, 195, 74,"+opacidad+")",
        "rgba(205, 220, 57,"+opacidad+")",
        "rgba(255, 235, 59,"+opacidad+")",
        "rgba(255, 193, 7,"+opacidad+")",
        "rgba(255, 152, 0,"+opacidad+")",
        "rgba(255, 87, 34,"+opacidad+")",
        "rgba(121, 85, 72,"+opacidad+")",
        "rgba(158, 158, 158, "+opacidad+")",
        "rgba(96, 125, 139,"+opacidad+")"
    ];
    var color = colores[indice];
    return color;
}

function getRandomColor(opacidad) {
    var indice =  Math.floor((Math.random() * 18));
    colores = [
        "rgba(244, 67, 54,"+opacidad+")",
        "rgba(233, 30, 99,"+opacidad+")",
        "rgba(156, 39, 176,"+opacidad+")",
        "rgba(103, 58, 183,"+opacidad+")",
        "rgba(63, 81, 181,"+opacidad+")",
        "rgba(33, 150, 243,"+opacidad+")",
        "rgba(3, 169, 244,"+opacidad+")",
        "rgba(0, 188, 212,"+opacidad+")",
        "rgba(0, 150, 136,"+opacidad+")",
        "rgba(76, 175, 80,"+opacidad+")",
        "rgba(139, 195, 74,"+opacidad+")",
        "rgba(205, 220, 57,"+opacidad+")",
        "rgba(255, 235, 59,"+opacidad+")",
        "rgba(255, 193, 7,"+opacidad+")",
        "rgba(255, 152, 0,"+opacidad+")",
        "rgba(255, 87, 34,"+opacidad+")",
        "rgba(121, 85, 72,"+opacidad+")",
        "rgba(158, 158, 158, "+opacidad+")",
        "rgba(96, 125, 139,"+opacidad+")"
    ];
    var color = colores[indice];
    return color;
}