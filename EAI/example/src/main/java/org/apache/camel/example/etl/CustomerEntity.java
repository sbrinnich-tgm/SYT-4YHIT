/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.etl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Repraesentiert einen Customer.
 * Es koennen welche Ausgelesen oder Erstellt werden.
 * 
 * @version 2015-02-28
 * @author Apache Camel
 */
@Entity(name = "customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = "findCustomerByUsername", query = "SELECT c FROM customer c WHERE c.userName = :userName")
public class CustomerEntity {
    @XmlAttribute
    private Long id;
    private String userName;
    private String firstName;
    private String surname;
    private String street;
    private String city;
    private String zip;
    private String phone;

    /**
     * ID welche vermutlich als PK dient
     * 
     * @return ID
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /**
     * Setzt die ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gibt die gespeicherte Stadt zurueck
     * @return Stadt
     */
    public String getCity() {
        return city;
    }

    /**
     * Setzt die Stadt
     * @param city Stadt
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gibt den gespeicherten Vornamen zurueck
     * @return Vorname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setzt den Vornamen
     * @param firstName Vorname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gibt die gespeicherte Telefonnummer zurueck
     * @return Telefonnummer
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Setzt die Telefonnummer
     * @param phone Telefonnummer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gibt die gespeicherte Strasse zurueck
     * @return Strasse
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setzt die Strasse
     * @param street Strasse
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gibt den gespeicherten Nachnamen zurueck
     * @return Nachname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setzt den Nachnamen
     * @param surname Nachname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gibt den gespeicherten Username zurueck
     * @return Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setzt den Benutzernamen
     * @param userName Benutzername
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gibt die gespeicherte Postleitzahl zurueck
     * @return Postleitzahl
     */
    public String getZip() {
        return zip;
    }

    /**
     * Setzt die Postleitzahl
     * @param zip Postleitzahl
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Gibt den ganzen Datensatz als String zurueck
     * @return Datensatz als String
     */
    public String toString() {
        return "Customer[userName: " + getUserName() + " firstName: " + getFirstName() + " surname: " + getSurname() + "]";
    }

}