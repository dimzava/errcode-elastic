import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import errcode.elastic.AppModule
import errcode.elastic.ElasticSearchConfig
import errcode.elastic.LogEvent
import errcode.elastic.SearchService
import ratpack.config.ConfigData
import ratpack.groovy.template.TextTemplateModule
import ratpack.server.ReloadInformant

import static ratpack.groovy.Groovy.groovyTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
	bindings {
        ConfigData configData = ConfigData.of(new JSR310Module())
                .props("${serverConfig.baseDir.file}/app.properties").build()
        bindInstance(ReloadInformant, configData)
        bindInstance(ElasticSearchConfig, configData.get('/es', ElasticSearchConfig))
        add AppModule
        add TextTemplateModule
  	}  
  	
  	handlers {
        get(':errcode?') { SearchService searchService ->
            String errcode = pathTokens["errcode"]
            Map model = [errcode: errcode]
            if(!errcode) {
                render groovyTemplate(model, "index.html")
            } else {
                searchService.findByErrcode(errcode).then { LogEvent logEvent ->
                    if(logEvent) {
                        model['logEvent'] = logEvent
                    }
                    render groovyTemplate(model, "index.html")
                }
            }
        }

        assets "public"
  	}
}
