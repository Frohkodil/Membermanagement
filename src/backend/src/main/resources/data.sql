--@formatter:off

-- FamilyDiscount
INSERT INTO FAMILYDISCOUNT (ID, DISCOUNT) VALUES (hibernate_sequence.nextval, 3);

--MembershipTypes
INSERT INTO MEMBERSHIPTYPE (ID, NAME, ANNUALFEE) VALUES ( hibernate_sequence.nextval,  'Vollmitgliedschaft', 25);
INSERT INTO MEMBERSHIPTYPE (ID, NAME, ANNUALFEE) VALUES ( hibernate_sequence.nextval,  'Ermäßigt', 23);
INSERT INTO MEMBERSHIPTYPE (ID, NAME, ANNUALFEE) VALUES ( hibernate_sequence.nextval,  'Jugendliche(r)', 15);
INSERT INTO MEMBERSHIPTYPE (ID, NAME, ANNUALFEE) VALUES ( hibernate_sequence.nextval,  'Fördermitglied', 10);

--Membership
INSERT INTO MEMBERSHIP (ID, STARTDATE, ENDDATE, CANCELLATIONDATE, MEMBERSHIPTYPE_ID) VALUES ( hibernate_sequence.nextval, 2021-10-15, NULL, NULL, SELECT ID FROM MEMBERSHIPTYPE WHERE NAME = 'Vollmitgliedschaft');

--PaymentHistory
INSERT INTO PAYMENTHISTORY (ID, PAYED, FEEPAYED, DATEOFPAYMENT,YEAR) VALUES ( hibernate_sequence.nextval, true, 25, 2021-10-16, 2021);

--Members
INSERT INTO MEMBER (ID, FIRSTNAME, LASTNAME, POSTALCODE, CITY, STREET, STREETNUMBER, DATEOFBIRTH, MEMBERSHIP, IBAN, PAYMENTHISTORIES, FAMILYMEMBER) VALUES (hibernate_sequence.nextval, 'Hans', 'Wurst', '25336', 'Elmshorn', 'Westerstraße', '50', );