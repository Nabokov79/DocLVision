CREATE TABLE IF NOT EXISTS CITY
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    city VARCHAR                                 NOT NULL,
    CONSTRAINT pk_city PRIMARY KEY (id),
    CONSTRAINT UQ_CITY UNIQUE (city)
);

CREATE TABLE IF NOT EXISTS ADDRESSES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    city_id         BIGINT                                  NOT NULL,
    street          VARCHAR                                 NOT NULL,
    house_number    INTEGER                                 NOT NULL,
    building_number INTEGER,
    letter          VARCHAR,
    CONSTRAINT pk_address PRIMARY KEY (id),
    CONSTRAINT UQ_ADDRESSES UNIQUE (city_id, street, house_number),
    CONSTRAINT FK_ADDRESSES_ON_CITY FOREIGN KEY (city_id) REFERENCES city (id)
);

CREATE TABLE IF NOT EXISTS REQUISITES
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    address_id BIGINT,
    index      INTEGER,
    phone      VARCHAR,
    fax        VARCHAR,
    email      VARCHAR,
    CONSTRAINT pk_requisites PRIMARY KEY (id),
    CONSTRAINT FK_DEPARTMENTS_ON_ADDRESSES FOREIGN KEY (address_id) REFERENCES addresses (id)
);

CREATE TABLE IF NOT EXISTS ORGANIZATIONS
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    organization            VARCHAR                                 NOT NULL,
    short_name_organization VARCHAR                                 NOT NULL,
    requisites_id           BIGINT,
    CONSTRAINT pk_organization PRIMARY KEY (id),
    CONSTRAINT UQ_ORGANIZATIONS UNIQUE (organization),
    CONSTRAINT FK_ORGANIZATIONS_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id)
);

CREATE TABLE IF NOT EXISTS BRANCHES
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    branch            VARCHAR                                 NOT NULL,
    short_name_branch VARCHAR                                 NOT NULL,
    requisites_id     BIGINT,
    organization_id   BIGINT                                  NOT NULL,
    CONSTRAINT pk_branch PRIMARY KEY (id),
    CONSTRAINT UQ_BRANCHES UNIQUE (branch),
    CONSTRAINT FK_BRANCHES_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id),
    CONSTRAINT FK_BRANCHES_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id)
);

CREATE TABLE IF NOT EXISTS DEPARTMENTS
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    department            VARCHAR                                 NOT NULL,
    short_name_department VARCHAR                                 NOT NULL,
    department_number     INTEGER,
    requisites_id         BIGINT,
    branch_id             BIGINT                                  NOT NULL,
    CONSTRAINT pk_department PRIMARY KEY (id),
    CONSTRAINT UQ_DEPARTMENTS UNIQUE (department, department_number),
    CONSTRAINT FK_DEPARTMENTS_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id)
);

CREATE TABLE IF NOT EXISTS BUILDINGS
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    address_id    BIGINT                                  NOT NULL,
    building_type VARCHAR                                 NOT NULL,
    branch        VARCHAR                                 NOT NULL,
    login         VARCHAR,
    department_id BIGINT                                  NOT NULL,
    requisites_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_building PRIMARY KEY (id),
    CONSTRAINT UQ_BUILDINGS UNIQUE (building_type, branch, login),
    CONSTRAINT FK_BUILDINGS_ON_ADDRESSES FOREIGN KEY (address_id) REFERENCES addresses (id),
    CONSTRAINT FK_BUILDINGS_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id)
);

CREATE TABLE IF NOT EXISTS CONTROL_TYPES
(
    id                      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name_type_control       VARCHAR                                 NOT NULL,
    short_name_type_control VARCHAR                                 NOT NULL,
    CONSTRAINT pk_typeControl PRIMARY KEY (id),
    CONSTRAINT UQ_CONTROL_TYPES UNIQUE (name_type_control)
);

CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR                                 NOT NULL,
    patronymic      VARCHAR                                 NOT NULL,
    surname         VARCHAR                                 NOT NULL,
    post            VARCHAR                                 NOT NULL,
    requisites_id   BIGINT,
    organization_id BIGINT                                  NOT NULL,
    branch_id       BIGINT                                  NOT NULL,
    department_id   BIGINT                                  NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id),
    CONSTRAINT UQ_EMPLOYEES UNIQUE (name, patronymic, surname),
    CONSTRAINT FK_EMPLOYEES_ON_REQUISITES FOREIGN KEY (requisites_id) REFERENCES requisites (id),
    CONSTRAINT FK_EMPLOYEES_ON_ORGANIZATIONS FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_EMPLOYEES_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id),
    CONSTRAINT FK_EMPLOYEES_ON_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id)
);

CREATE TABLE IF NOT EXISTS MEASURING_TOOLS
(
    id                     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    toll                   VARCHAR                                 NOT NULL,
    model                  VARCHAR                                 NOT NULL,
    work_number            VARCHAR,
    purpose                VARCHAR,
    manufacturing          DATE,
    exploitation           DATE,
    manufacturer_id        BIGINT,
    measuring_range        VARCHAR,
    characteristics        VARCHAR,
    tool_owner_id          BIGINT                                  NOT NULL,
    verification_date      DATE,
    next_verification_date DATE,
    organization_id        BIGINT,
    certificate_number     VARCHAR,
    registry               VARCHAR,
    note                   VARCHAR,
    control_type_id        BIGINT                                  NOT NULL,
    employee_id            BIGINT,
    CONSTRAINT pk_measuringTool PRIMARY KEY (id),
    CONSTRAINT UQ_MEASURING_TOOL UNIQUE (toll, model, work_number),
    CONSTRAINT FK_MEASURING_TOOL_ON_ORGANIZATION
        FOREIGN KEY (manufacturer_id) REFERENCES organizations (id),
    FOREIGN KEY (tool_owner_id) REFERENCES organizations (id),
    FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_MEASURING_TOOL_ON_CONTROL_TYPES FOREIGN KEY (control_type_id) REFERENCES control_types (id),
    CONSTRAINT FK_MEASURING_TOOL_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS CERTIFICATES
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    document_type      VARCHAR                                 NOT NULL,
    certificate_number VARCHAR                                 NOT NULL,
    control_type_id    BIGINT                                  NOT NULL,
    level              VARCHAR                                 NOT NULL,
    start_date         DATE                                    NOT NULL,
    end_date           DATE                                    NOT NULL,
    points             VARCHAR,
    organization_id    BIGINT                                  NOT NULL,
    employee_id        BIGINT                                  NOT NULL,
    CONSTRAINT pk_certificate PRIMARY KEY (id),
    CONSTRAINT UQ_CERTIFICATES UNIQUE (control_type_id, employee_id),
    CONSTRAINT FK_CERTIFICATE_ON_CONTROL_TYPES FOREIGN KEY (control_type_id) REFERENCES control_types (id),
    CONSTRAINT FK_CERTIFICATE_ON_ORGANIZATION FOREIGN KEY (organization_id) REFERENCES organizations (id),
    CONSTRAINT FK_CERTIFICATE_ON_EMPLOYEES FOREIGN KEY (employee_id) REFERENCES employees (id)
);

CREATE TABLE IF NOT EXISTS LICENSES
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    organization_id   BIGINT,
    branch_id         BIGINT,
    department_id     BIGINT,
    document_type     VARCHAR                                 NOT NULL,
    license_number    VARCHAR                                 NOT NULL,
    start_date        DATE                                    NOT NULL,
    end_date          DATE                                    NOT NULL,
    issued_license_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_license PRIMARY KEY (id),
    CONSTRAINT UQ_LICENSES UNIQUE (organization_id, branch_id, department_id, document_type),
    CONSTRAINT FK_LICENSES_ON_ORGANIZATIONS
        FOREIGN KEY (organization_id) REFERENCES organizations (id),
    FOREIGN KEY (issued_license_id) REFERENCES organizations (id),
    CONSTRAINT FK_LICENSES_ON_BRANCHES FOREIGN KEY (branch_id) REFERENCES branches (id),
    CONSTRAINT FK_LICENSES_ON_DEPARTMENTS FOREIGN KEY (department_id) REFERENCES departments (id)
);