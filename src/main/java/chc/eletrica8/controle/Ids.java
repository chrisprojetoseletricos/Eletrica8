/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chc.eletrica8.controle;

/**
 *
 * @author chris
 */
public class Ids {

    private static Integer idProjeto = 0;
    private static Integer idFonte = 0;
    private static Integer idQuadro = 0;
    private static Integer idCircuito = 0;
    private static Integer idEquipamento = 0;
    private static Integer idCondutor = 0;
    private static Integer idCurto = 0;
    private static Integer idConcessionaria = 0;
    private static Integer idRamalLigacao = 0;
    private static Integer idCarga = 0;

    public static void imprimiIds() {
        System.out.println("idProjeto: " + getIdProjeto());
        System.out.println("idFonte: " + getIdFonte());
        System.out.println("idQuadro: " + getIdQuadro());
        System.out.println("idCircuito: " + getIdCircuito());
        System.out.println("idEquipamento: " + getIdEquipamento());
        System.out.println("idCondutor: " + getIdCondutor());
        System.out.println("idCurto: " + getIdCurto());
        System.out.println("idConcessionaria: " + getIdConcessionaria());
        System.out.println("idRamalLigacao: " + getIdRamalLigacao());
        System.out.println("idCarga: " + getIdCarga());
    }

    public static Integer getIdCarga() {
        return idCarga;
    }

    public static void setIdCarga(Integer idCarga) {
        Ids.idCarga = idCarga;
    }

    /**
     * @return the idProjeto
     */
    public static Integer getIdProjeto() {
        return idProjeto;
    }

    /**
     * @param aIdProjeto the idProjeto to set
     */
    public static void setIdProjeto(Integer aIdProjeto) {
        idProjeto = aIdProjeto;
    }

    /**
     * @return the idFonte
     */
    public static Integer getIdFonte() {
        return idFonte;
    }

    /**
     * @param aIdFonte the idFonte to set
     */
    public static void setIdFonte(Integer aIdFonte) {
        idFonte = aIdFonte;
    }

    /**
     * @return the idQuadro
     */
    public static Integer getIdQuadro() {
        return idQuadro;
    }

    /**
     * @param aIdQuadro the idQuadro to set
     */
    public static void setIdQuadro(Integer aIdQuadro) {
        idQuadro = aIdQuadro;
    }

    /**
     * @return the idCircuito
     */
    public static Integer getIdCircuito() {
        return idCircuito;
    }

    /**
     * @param aIdCircuito the idCircuito to set
     */
    public static void setIdCircuito(Integer aIdCircuito) {
        idCircuito = aIdCircuito;
    }

    /**
     * @return the idEquipamento
     */
    public static Integer getIdEquipamento() {
        return idEquipamento;
    }

    /**
     * @param aIdEquipamento the idEquipamento to set
     */
    public static void setIdEquipamento(Integer aIdEquipamento) {
        idEquipamento = aIdEquipamento;
    }

    /**
     * @return the idCondutor
     */
    public static Integer getIdCondutor() {
        return idCondutor;
    }

    /**
     * @param aIdCondutor the idCondutor to set
     */
    public static void setIdCondutor(Integer aIdCondutor) {
        idCondutor = aIdCondutor;
    }

    /**
     * @return the idCurto
     */
    public static Integer getIdCurto() {
        return idCurto;
    }

    /**
     * @param aIdCurto the idCurto to set
     */
    public static void setIdCurto(Integer aIdCurto) {
        idCurto = aIdCurto;
    }

    /**
     * @return the idConcessionaria
     */
    public static Integer getIdConcessionaria() {
        return idConcessionaria;
    }

    /**
     * @param aIdConcessionaria the idConcessionaria to set
     */
    public static void setIdConcessionaria(Integer aIdConcessionaria) {
        idConcessionaria = aIdConcessionaria;
    }

    /**
     * @return the idRamalLigacao
     */
    public static Integer getIdRamalLigacao() {
        return idRamalLigacao;
    }

    /**
     * @param aIdRamalLigacao the idRamalLigacao to set
     */
    public static void setIdRamalLigacao(Integer aIdRamalLigacao) {
        idRamalLigacao = aIdRamalLigacao;
    }
}
