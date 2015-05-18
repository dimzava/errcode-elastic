package errcode.elastic

class LogEvent {
    String errCode
    String timestamp
    String host
    String logFilePath
    String appId
    String appModule
    String logLevel
    String thread
    String logger
    String stackTrace
    String message
    String requestId
    String ewaFileType
    String ewaFileExtension
    String ewaLocation
    String ewaAction
    String userAgent

    LogEvent(Map json) {
        this.errCode = json.ErrCode
        this.timestamp = json."@timestamp"
        this.host = json.host
        this.logFilePath = json.file
        this.appId = json.appId
        this.appModule = json.Module
        this.logLevel = json.logLevel
        this.thread = json.thread
        this.logger = json.logger
        this.stackTrace = json.stackTrace
        this.message = json.Message
        this.requestId = json.RequestID
        this.ewaFileType = json.FileType
        this.ewaFileExtension = json.Extension
        this.ewaLocation = json.Location
        this.ewaAction = json.Action
        this.userAgent = json.UserAgent
        this.userAgent = json.UserAgent
    }
}
