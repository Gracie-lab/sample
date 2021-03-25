package com.medicine.medicineProject.models;

public enum ZoneCoverage {

    IKEJA_BERGER, YABA_LAGOSMAINLAND, OBALENDE_VICTORIAISLAND, LEKKI, AJAH, IKORODU,
    SURULERE, EGEDA_IYANAIPAJA, APAPA_AJEROMIIFELODUN, MUSHIN_SHOMOLU_OSHODI_ISOLO,
    LAGOSISLAND_IKOYI, AGEGE_IFAKOIJAYE, OJO_AMOWUODOFIN_BADAGRY, KOSOFE_KETU, IBAFO;


    @Override
    public String toString() {
        switch (this){
            case IKEJA_BERGER:return "Ikeja-Berger";
            case YABA_LAGOSMAINLAND: return "Yaba-LagosMainLand";
            case OBALENDE_VICTORIAISLAND: return "Obalende-VictoriaIsland";
            case LEKKI:return "Lekki";
            case AJAH:return "Ajah";
            case IKORODU:return "Ikorodu";
            case SURULERE:return "Surulere";
            case EGEDA_IYANAIPAJA:return "Egeda-IyanaIpaja";
            case APAPA_AJEROMIIFELODUN:return "Apapa-AjeromiIfelodun";
            case MUSHIN_SHOMOLU_OSHODI_ISOLO:return "Mushin-Shomolu-Oshodi-Isolo";
            case LAGOSISLAND_IKOYI:return "LagosIsland-Ikoyi";
            case AGEGE_IFAKOIJAYE:return "Agege-IfakoIjaye";
            case OJO_AMOWUODOFIN_BADAGRY:return "Ojo-AmowuOdofin-Badagry";
            case KOSOFE_KETU:return "Kosofe-Ketu";
            case IBAFO:return "Ibafo";
            default:return null;
        }
    }


}
