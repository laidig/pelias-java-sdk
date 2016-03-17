pelias-android-sdk
==================

Java sdk for pelias

## Usage

Pelias Java Sdk is a wrapper for Pelias that is derived from the Pelias Android SDK

#### Initialization

Pelias provides a simple singleton which can be statically included in your application.

```java
import static net.transitdata.pelias_java_sdk.getPelias;

getPelias().<method> ...

```

#### Suggest

```java
import static net.transitdata.pelias_java_sdk.getPelias;

getPelias().suggest("term to search", Callback<Result>);
```

#### Search

```java
import static net.transitdata.pelias_java_sdk.getPelias;

getPelias().search("term to search", "<viewbox>", Callback<Result>);
````

#### Custom Endpoint

If you have deployed your own instance of pelias described [here][2] you can set it on the class before initializing

```java
getPeliasWithEndpoint("http://your-pelias-domain.com").search("term to search", "<viewbox>", Callback<Result>);
```


#### Testing

The current stratedgy for testing involves mocking the service instance which is an retrofit interface which describes the paths to the API.

You can extend Pelias where you can inject you mock object ... we use Mockito which is awesome

```java
package net.transitdata.pelias_java_sdk;

import org.mockito.Mockito;

public final class TestPelias extends Pelias {
    protected TestPelias(PeliasService service) {
        super(service);
    }

    public static PeliasService getPeliasMock() {
        PeliasService service = Mockito.mock(PeliasService.class);
        instance = new TestPelias(service);
        return service;
    }
}
```

Then when you want to interact with Pelias in stests you can just feed it a mock

```java
@Captor
@SuppressWarnings("unused")
ArgumentCaptor<Callback<Result>> peliasCallback;
    

MockitoAnnotations.initMocks(this);
PeliasService peliasServiceMock = TestPelias.getPeliasMock();
    
Mockito.verify(peliasServiceMock)
  .getSearch(Mockito.eq("Empire State Building"), Mockito.anyString(), peliasCallback.capture());

```
With this you can verify which arguments got sent to the service ... and then to test success or failure path you can can capture the callback and retrive the value and call respective callbacks with your own object mocks you want to have returned in your test.

success

```java
peliasCallback.getValue().success(results, response);
```

failure
```java
peliasCallback.getValue().failure(RetrofitError.unexpectedError("", null));
```


## Install

#### Download Jar
...
