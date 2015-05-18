package errcode.elastic

import com.google.inject.AbstractModule
import com.google.inject.Scopes

class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(SearchService).to(ElasticSearchService).in(Scopes.SINGLETON)
    }
}
