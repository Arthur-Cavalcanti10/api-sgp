package br.com.sgp.api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import dto.MensagemErroApi;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        MensagemErroApi mensagemErroApi = new MensagemErroApi(HttpStatus.BAD_REQUEST, erros);

        return ResponseEntity.badRequest().body(mensagemErroApi);
    }
 @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<MensagemErroApi> handleUsuarioNaoEncontradoException(
      UsuarioNaoEncontradoException ex, WebRequest request) {
        MensagemErroApi msgErroApi = new MensagemErroApi(HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msgErroApi);
      }
}