package com.envision.login.tests;

import org.testng.annotations.*;

public class UnderstandingTestNGAnnotations extends BeforeAfterAnnotations {



    @Test
    public void doSomething(){
        System.out.println("Iam running a test");
    }

    @Test
    public void doSomethingAgain(){
        System.out.println("Iam running a test AGAIN");
    }




}
