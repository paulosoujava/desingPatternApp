package com.paulo.designpatterns.domain.utils

enum class Page(val title:String, val content: String, val patterns: List<String>){
    CREATIONAL("Creational","Design Creational", listOf(
        Consts.FACTORY_METHOD.title,
        Consts.ABSTRACT_METHOD.title,
        Consts.BUILDER.title,
        Consts.PROTOTYPE.title,
        Consts.SINGLETON.title,
    )),
    BEHAVIORAL("Behavioral","Design Behavioral", listOf(
        "Chain of Resposability",
        "Command",
        "Iterator",
        "Mediator",
        "Memento",
        "Observer",
        "State",
        "Strategy",
        "Template Method",
        "Visitor",
    )),
    STRUCTURAL("Structural","Design Structural", listOf(
        "Adapter",
        "Bridge",
        "Composite",
        "Decorator",
        "Facade",
        "Flayweight",
        "Proxy",
    )),
    SOLID("Solid","SOLID PRINCIPLE", listOf(
        "Single Responsiblity ",
        "Open-Closed ",
        "Liskov Substitution ",
        "Interface Segregation  ",
        "Dependency Inversion "
    )),
    FONT(title = "Font", content = "Fonte de dados", listOf(
        "GURU*https://refactoring.guru/pt-br/design-patterns",
        "MEDIUM*https://medium.com/desenvolvendo-com-paixao/o-que-%C3%A9-solid-o-guia-completo-para-voc%C3%AA-entender-os-5-princ%C3%ADpios-da-poo-2b937b3fc530"
    ))
}