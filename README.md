# PokeHub
This is my personal project to learn about new tech for me. This is a sample project that uses Graphql API's with Rx implementation. If you are bored with REST API's and create a model class for every new API, go forward! :)

<img align="right" height="300" src="https://firebasestorage.googleapis.com/v0/b/events-c4167.appspot.com/o/pokehub%2FScreen%20Shot%202019-04-14%20at%2017.59.42.png?alt=media&token=99a16a3f-0873-4e7a-98c4-a81b915eba0d"></img>
### Libraries
 - [Graphql Apollo Client](https://github.com/apollographql/apollo-android)
 - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/)
 - [Android Data Binding](https://developer.android.com/topic/libraries/data-binding/)
 - [Dagger](https://google.github.io/dagger/)
 - [Glide](https://github.com/bumptech/glide)
 - [Rx](https://github.com/ReactiveX/RxJava)
 - [Lottie](https://github.com/airbnb/lottie-android)

<br/>
<br/>

# So what is the differences between REST?
As I am an Android Developer, I can compare them in client side.Rest is network architectural concept, but Graphql is a query language.
 
 - In Apollo-Android, you don't have to create your own classes to retrieve network response.
 - Once you define your query like below, it will generate your model class automatically. That is really good thing, if you have knowledge  about traditional way.
```
query PokemonRepository($first:Int!){
  pokemons(first:$first) {
    id
    image
    name
  }
}
```
The above query is equalivent of some REST API. This is the way to define a new API in Graphql. And as you see, it's so simple.

# RX implementation
 With Apollo-Rx support dependency, you can use Apollo Calls with Rx. For this, you must add this,
 ```
 implementation "com.apollographql.apollo:apollo-rx2-support:$apolloVersion"
 ```
 This dependency is in the end of the Apollo's github page. So it's pretty hard to find.
 After add this dependecy, the rest of work is below,
  ```
  var observable = Rx2Apollo.from(apolloClient.query(PokemonRepositoryQuery.builder().first(count).build()))
            .map {  
                 it.data()?.pokemons()  
            }
   ```
That's it. Now you have your observable and you must just subscribe it and observe the incoming datas :)

# Warning for Dagger2 fans
While setting up the project, you must add the graphql folder under the `app/src/graphql` folder. You can just put the `API.graphql` file under there and you can use it in proper way. But if you want use dagger, you must put it under the `app/src/graphql/{PACKAGENAME}/` folder. I wasn't careful while I'm doing this and I struggled for this issue about 2 days. Dagger does not recognize your auto-generated classes if you don't put them under the `app/src/graphql/{PACKAGENAME}/` folder.
 
 ## Outputs
 <img height="400" src="https://firebasestorage.googleapis.com/v0/b/events-c4167.appspot.com/o/pokehub%2FScreen%20Shot%202019-04-14%20at%2018.45.59.png?alt=media&token=eef406f0-2efb-439a-860b-30f94d091149"></img>

## Thanks
Thanks for [@lucasbento](https://github.com/lucasbento) to provide these useful API's.
[Graphql Pokemon API](https://github.com/lucasbento/graphql-pokemon)

## Licence
```
Copyright 2019 Tayfun CESUR

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
