package gestao_de_eventos;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizador")
public class Utilizador {

    public enum TipoUtilizador { ADMIN, ORGANIZADOR, PARTICIPANTE }
    public enum EstadoUtilizador { ATIVO, INATIVO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizador")
    private Integer id;

    @Column(name = "nome_utilizador", nullable = false, length = 100)
    private String nomeUtilizador;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "senha_utilizador", nullable = false, length = 255)
    private String senhaUtilizador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_utilizador", nullable = false, length = 20)
    private TipoUtilizador tipoUtilizador = TipoUtilizador.PARTICIPANTE;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_utilizador", nullable = false, length = 20)
    private EstadoUtilizador estadoUtilizador = EstadoUtilizador.ATIVO;

    public Utilizador() {}

    // Getters/Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeUtilizador() { return nomeUtilizador; }
    public void setNomeUtilizador(String nomeUtilizador) { this.nomeUtilizador = nomeUtilizador; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenhaUtilizador() { return senhaUtilizador; }
    public void setSenhaUtilizador(String senhaUtilizador) { this.senhaUtilizador = senhaUtilizador; }

    public TipoUtilizador getTipoUtilizador() { return tipoUtilizador; }
    public void setTipoUtilizador(TipoUtilizador tipoUtilizador) { this.tipoUtilizador = tipoUtilizador; }

    public EstadoUtilizador getEstadoUtilizador() { return estadoUtilizador; }
    public void setEstadoUtilizador(EstadoUtilizador estadoUtilizador) { this.estadoUtilizador = estadoUtilizador; }
}