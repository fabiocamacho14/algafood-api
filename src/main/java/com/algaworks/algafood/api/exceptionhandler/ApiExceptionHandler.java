package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.ValidacaoException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_SISTEMA = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
            ", entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
//        List<StackTraceElement> lista =  Arrays.stream(rootCause.getStackTrace()).toList();
//        lista.forEach(System.out::println);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);

        } else if (rootCause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedPropertyException((UnrecognizedPropertyException) rootCause, headers, status, request);

        } else if (rootCause instanceof IgnoredPropertyException) {
            return handleIgnoredPropertyException((IgnoredPropertyException) rootCause, headers, status, request);
        }

        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
        ProblemType problemType =  ProblemType.MENSAGEM_INCOMPREENSIVEL;

        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now()).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe" +
                " um valor compatível com o tipo '%s'.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage("Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
                        ", entre em contato com o administrador do sistema.")
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleIgnoredPropertyException (IgnoredPropertyException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("A propriedade '%s' está configurada para ser ignorada na classe '%s'. Corrija", ex.getPropertyName(), ex.getReferringClass().getSimpleName());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("Propriedade '%s' não reconhecida na classe '%s'.",ex.getPropertyName(), ex.getReferringClass().getSimpleName());
        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e" +
                " informe um valor compatível com o tipo %s.", ex.getParameter().getParameterName(), ex.getValue().toString(), ex.getRequiredType().getSimpleName());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now())
                .userMessage("Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
                        ", entre em contato com o administrador do sistema.")
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        ProblemType problemType =  ProblemType.RECURSO_NAO_ENCONTRADO;

        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.NEGOCIO_EXCEPTION;

        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;

        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptionQualquer(Exception ex, WebRequest request) {

        String detail = MSG_ERRO_SISTEMA;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Problem problem = createProblemBuilder(status, problemType, detail, LocalDateTime.now()).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.", ex.getRequestURL());
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        Problem problem = createProblemBuilder(HttpStatus.valueOf(status.value()), problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(ValidacaoException.class)
    private ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
//        return handleMethodArgumentNotValid(ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        return handleExceptionInternal(ex, getProblem(ex.getBindingResult()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex, getProblem(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
    }

    private Problem getProblem(BindingResult bindingResult) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        HttpStatus statusBad = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        List<Problem.Object> problemObjects = bindingResult.getAllErrors()
                .stream()
                .map(error -> {
                    String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                    String name = error.getObjectName();

                    if (error instanceof FieldError) {
                        name = ((FieldError) error).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).toList();

        return createProblemBuilder(statusBad, problemType, detail, LocalDateTime.now())
                .userMessage(MSG_ERRO_SISTEMA)
                .objects(problemObjects)
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        List<StackTraceElement> lista =  Arrays.stream(rootCause.getStackTrace()).toList();
        lista.forEach(System.out::println);

        if (body == null) {
            body =  Problem.builder()
                    .title(ex.getMessage())
                    .status(statusCode.value())
                    .build();
        } else if (body instanceof String) {
            body =  Problem.builder()
                    .title((String) body)
                    .status(statusCode.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail, LocalDateTime timeStamp) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timeStamp(timeStamp);
    }
}
