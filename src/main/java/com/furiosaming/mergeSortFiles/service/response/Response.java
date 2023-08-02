package com.furiosaming.mergeSortFiles.service.response;

import com.furiosaming.mergeSortFiles.service.constants.AppConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Response", description = "Ответ пользователю")
public class  Response<T> {

    @Schema(name = "status", description = "Статус ответа (успешно, либо одна из ошибок)")
    private boolean status;
    @Schema(name = "description", description = "Описание ошибки, либо просто успешно")
    private String description;
    @Schema(name = "result", description = "Полученные сущности в процессе обработки")
    private T result;

    public static class Builder<T> {
        private Response<T> response;

        public Builder() {
            response = new Response<>();
        }

        public Builder<T> success(T result, String message) {
            response.status = true;
            response.description = message;
            response.result = result;
            return this;
        }

        public Builder<T> missingOutputFile() {
            response.status = false;
            response.description = AppConstants.missingOutputFile;
            response.result = null;
            return this;
        }

        public Builder<T> missingInputFiles() {
            response.status = false;
            response.description = AppConstants.missingInputFile;
            response.result = null;
            return this;
        }

        public Builder<T> missingFilesDataType() {
            response.status = false;
            response.description = AppConstants.missingFilesDataType;
            response.result = null;
            return this;
        }

        public Builder<T> allFilesAreEmpty() {
            response.status = false;
            response.description = AppConstants.allFilesAreEmpty;
            response.result = null;
            return this;
        }

        public Response<T> build() {
            return response;
        }
    }

}
