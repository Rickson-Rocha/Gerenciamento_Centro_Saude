CREATE TABLE tb_consulta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT,
    medico_id INT,
    data_consulta DATE NOT NULL,
    diagnostico TEXT,
    prescricao TEXT,
    FOREIGN KEY (paciente_id) REFERENCES tb_paciente(id),
    FOREIGN KEY (medico_id) REFERENCES tb_medico(id)
);