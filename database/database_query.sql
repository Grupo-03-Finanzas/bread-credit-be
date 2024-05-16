CREATE TABLE admin (
                       admin_id int  NOT NULL,
                       business_name varchar(32)  NOT NULL,
                       business_type varchar(32)  NOT NULL,
                       CONSTRAINT admin_pk PRIMARY KEY (admin_id)
);
CREATE TABLE creditaccount (
                               creditaccount_id serial  NOT NULL,
                               customer_id int  NOT NULL,
                               admin_id int  NOT NULL,
                               active boolean  NOT NULL,
                               max_credit decimal(16,12)  NOT NULL,
                               current_credit decimal(16,12)  NOT NULL,
                               credit_type_of_rate varchar(3)  NOT NULL,
                               credit_rate decimal(16,12)  NOT NULL,
                               credit_compounding int  NOT NULL,
                               invoice_penalty_rate_type varchar(3)  NOT NULL,
                               invoice_penalty_rate decimal(16,12)  NOT NULL,
                               invoice_penalty_compouding int  NOT NULL,
                               installment_penalty_rate_type varchar(3)  NOT NULL,
                               installment_penalty_rate decimal(16,12)  NOT NULL,
                               installment_penalty_compouding int  NOT NULL,
                               invoice_compensatory_rate_type varchar(3)  NOT NULL,
                               invoice_compensatory_rate decimal(16,12)  NOT NULL,
                               invoice_compensatory_compouding int  NOT NULL,
                               installment_compensatory_rate_type varchar(3)  NOT NULL,
                               installment_compensatory_rate decimal(16,12)  NOT NULL,
                               installment_compensatory_compouding int  NOT NULL,
                               CONSTRAINT creditaccount_pk PRIMARY KEY (creditaccount_id)
);
CREATE TABLE customer (
                          customer_id int  NOT NULL,
                          address varchar(64)  NOT NULL,
                          CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);
CREATE TABLE installment (
                             installment_id serial  NOT NULL,
                             purchase_id int  NOT NULL,
                             payment_id int,
                             due_date date  NOT NULL,
                             amount decimal(16,2)  NOT NULL,
                             CONSTRAINT installment_pk PRIMARY KEY (installment_id)
);
CREATE TABLE invoice (
                         invoice_id serial  NOT NULL,
                         payment_id int,
                         due_date date  NOT NULL,
                         amount decimal(16,2)  NOT NULL,
                         CONSTRAINT invoice_pk PRIMARY KEY (invoice_id)
);
CREATE TABLE payment (
                         payment_id serial  NOT NULL,
                         amount decimal(16,12)  NOT NULL,
                         time timestamp  NOT NULL,
                         CONSTRAINT payment_pk PRIMARY KEY (payment_id)
);
CREATE TABLE product (
                         product_id serial  NOT NULL,
                         admin_id int  NOT NULL,
                         name varchar(32)  NOT NULL,
                         price decimal(16,2)  NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (product_id)
);
CREATE TABLE product_purchase (
                                  product_id int  NOT NULL,
                                  purchase_id int  NOT NULL,
                                  count decimal(16,2)  NOT NULL,
                                  CONSTRAINT product_purchase_pk PRIMARY KEY (purchase_id,product_id)
);
CREATE TABLE purchase (
                          purchase_id serial  NOT NULL,
                          creditaccount_id int  NOT NULL,
                          initial_cost decimal(16,2)  NOT NULL,
                          time timestamp  NOT NULL,
                          installment_number int  NOT NULL,
                          credit_rate_type varchar(3)  NOT NULL,
                          credit_rate decimal(16,12)  NOT NULL,
                          credit_compouding int  NOT NULL,
                          penalty_rate_type varchar(3)  NOT NULL,
                          penalty_rate decimal(16,12)  NOT NULL,
                          penalty_compouding int  NOT NULL,
                          compensatory_rate_type varchar(3)  NOT NULL,
                          compensatory_rate decimal(16,12)  NOT NULL,
                          compensatory_compouding int  NOT NULL,
                          CONSTRAINT purchase_pk PRIMARY KEY (purchase_id)
);
CREATE TABLE purchase_invoice (
                                  purchase_id int  NOT NULL,
                                  invoice_id int  NOT NULL,
                                  CONSTRAINT purchase_invoice_pk PRIMARY KEY (purchase_id,invoice_id)
);
CREATE TABLE "user" (
                        user_id serial  NOT NULL,
                        first_name varchar(32)  NOT NULL,
                        last_name varchar(32)  NOT NULL,
                        dni varchar(8)  NOT NULL,
                        phone varchar(9)  NOT NULL,
                        email varchar(32)  NOT NULL,
                        password varchar(32)  NOT NULL,
                        CONSTRAINT user_pk PRIMARY KEY (user_id)
);
ALTER TABLE admin ADD CONSTRAINT admin_user
    FOREIGN KEY (admin_id)
        REFERENCES "user" (user_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE creditaccount ADD CONSTRAINT creditaccount_admin
    FOREIGN KEY (admin_id)
        REFERENCES admin (admin_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE creditaccount ADD CONSTRAINT creditaccount_customer
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE customer ADD CONSTRAINT customer_user
    FOREIGN KEY (customer_id)
        REFERENCES "user" (user_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE installment ADD CONSTRAINT installment_payment
    FOREIGN KEY (payment_id)
        REFERENCES payment (payment_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE installment ADD CONSTRAINT installment_purchase
    FOREIGN KEY (purchase_id)
        REFERENCES purchase (purchase_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE invoice ADD CONSTRAINT invoice_payment
    FOREIGN KEY (payment_id)
        REFERENCES payment (payment_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE product ADD CONSTRAINT product_admin
    FOREIGN KEY (admin_id)
        REFERENCES admin (admin_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE product_purchase ADD CONSTRAINT product_purchase_product
    FOREIGN KEY (product_id)
        REFERENCES product (product_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE product_purchase ADD CONSTRAINT product_purchase_purchase
    FOREIGN KEY (purchase_id)
        REFERENCES purchase (purchase_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE purchase ADD CONSTRAINT purchase_creditaccount
    FOREIGN KEY (creditaccount_id)
        REFERENCES creditaccount (creditaccount_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE purchase_invoice ADD CONSTRAINT purchase_invoice_invoice
    FOREIGN KEY (invoice_id)
        REFERENCES invoice (invoice_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;
ALTER TABLE purchase_invoice ADD CONSTRAINT purchase_invoice_purchase
    FOREIGN KEY (purchase_id)
        REFERENCES purchase (purchase_id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;