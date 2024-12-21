package org.example;

import net.datafaker.Faker;
import rx.Observable;

import java.time.LocalDateTime;
import java.util.List;

public class Main {

    private static final Faker FAKER = new Faker();
    private static final LocalDateTime localDateTime = LocalDateTime.now();

    //Programa que maneja subscripciones de una plataforma de redes sociales
    public static void main(String[] args) {
        // Obtiene los observables desde listas de Subscripciones
        Observable<SocialMediaSubscriptions> observableWebPage = Observable.from(getSubscriptionsFromWebPage());
        Observable<SocialMediaSubscriptions> observableMobileApp = Observable.from(getSubscriptionsFromWMobileApp());

        //Unifica los observables en un unico observable
        Observable.concat(observableWebPage, observableMobileApp)
                //Filtra solo las subscripciones relacionadas con youtube e instagram
                .filter(sub -> (sub.getChannel()==Channel.YOUTUBE || sub.getChannel()==Channel.INSTAGRAM))
                //crea una nueva isntancia agregandole la fecha de subscripcion
                .map(subFiltered -> new SocialMediaSubscriptions(subFiltered.getUser(), subFiltered.getChannel(), localDateTime))
                //las organiza por canal
                .sorted()
                //las agrupa según el canal
                .groupBy(subMapped -> subMapped.getChannel() == Channel.YOUTUBE ? "Video" : "reel")
                //muestra los grupos y su detalle
                .subscribe(group ->
                        group.subscribe((subscription) -> {
                    if(group.getKey().toString().equals("reel")) {
                        System.out.println("Vas a notificar una subscripcion de un reel: " + subscription.toString());
                    } else {
                        System.out.println("Vas a notificar una subscripcion de un video: " + subscription.toString());
                    }
        }));
    }

    private static List<SocialMediaSubscriptions> getSubscriptionsFromWebPage() {
        return List.of(new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.FACEBOOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.INSTAGRAM),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.TIKTOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE));
            }

    private static List<SocialMediaSubscriptions> getSubscriptionsFromWMobileApp() {
        return List.of(new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.FACEBOOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.FACEBOOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.INSTAGRAM),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.INSTAGRAM),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.TIKTOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.YOUTUBE),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.TIKTOK),
                new SocialMediaSubscriptions(FAKER.name().fullName(), Channel.INSTAGRAM));
    }

}

