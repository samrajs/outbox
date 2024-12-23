package com.sam.order;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.sam.order")
public class HexagonalArchitectureTest {

    @ArchTest
    public static final ArchRule domain_should_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..domain..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..application..");

    @ArchTest
    public static final ArchRule domain_should_not_depend_on_controller =
            noClasses()
                    .that()
                    .resideInAPackage("..domain..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule domain_should_not_depend_on_dataaccess =
            noClasses()
                    .that()
                    .resideInAPackage("..domain..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..dataaccess..");

        @ArchTest
        public static final ArchRule domain_should_not_depend_on_messaging =
                noClasses()
                        .that()
                        .resideInAPackage("..domain..")
                        .should()
                        .dependOnClassesThat()
                        .resideInAPackage("..dataaccess..");

    @ArchTest
    public static final ArchRule application_should_not_depend_on_controller =
            noClasses()
                    .that()
                    .resideInAPackage("..application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..controller..");

    @ArchTest
    public static final ArchRule application_should_not_depend_on_dataaccess =
            noClasses()
                    .that()
                    .resideInAPackage("..application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..dataaccess..");

    @ArchTest
    public static final ArchRule application_should_not_depend_on_messaging =
            noClasses()
                    .that()
                    .resideInAPackage("..application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..messaging..");

    @ArchTest
    public static final ArchRule messaging_should_not_depend_on_dataaccess =
            noClasses()
                    .that()
                    .resideInAPackage("..messaging..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..dataaccess..");

    @ArchTest
    public static final ArchRule dataaccess_should_not_depend_on_messaging =
            noClasses()
                    .that()
                    .resideInAPackage("..dataaccess..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..messaging..");

    /*@ArchTest
    public static final ArchRule dataaccess_should_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..dataaccess..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..application..");                    */
}