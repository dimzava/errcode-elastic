package errcode.elastic

import ratpack.exec.Promise

interface SearchService {
    Promise<LogEvent> findByErrcode(String errcode)
}
