/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commres;

/**
 *
 * @author DELL
 */
public enum Request {
    REGISTER,
    LOGIN,
    LOGOUT,
    SESSION_REQUEST_SEND,
    SESSION_REQUEST_ACK,
    ADMIN_INIT,
    ADMIN_INIT_RESP,
    ADMIN_NEW_REQ,
    SESSION_REQUEST_ACCEPTED,
    SESSION_REQUEST_ACCEPTED_ACK,
    ADMIN_ACCEPT_OR_REJECT_RESP,
    STUDENT_INIT,
     STUDENT_INIT_RESP,
    // STUDENT_ACCEPT_OR_REJECT_RESP,
     STUDENT_NEW_REQ,
       ADMIN_NEW_LIST,
    ADMIN_REG_ACK,
    MENTOR_REG_ACK
}
