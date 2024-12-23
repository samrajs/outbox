package com.sam.order;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.sam.payment")
public class HexagonalArchitectureTest {
    @ArchTest
    public static final ArchRule application_should_not_depend_on_messaging =
            noClasses()
                    .that()
                    .resideInAPackage("..application..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..dataaccess..");

    /*@ArchTest
    public static final ArchRule messaging_should_not_depend_on_application =
            noClasses()
                    .that()
                    .resideInAPackage("..messaging..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..application..");                    */
}