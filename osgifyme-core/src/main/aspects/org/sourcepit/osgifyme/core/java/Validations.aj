/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.ProviderSpecificBootstrap;

import org.apache.bval.jsr303.ApacheValidationProvider;
import org.apache.bval.jsr303.ApacheValidatorConfiguration;
import org.apache.bval.jsr303.DefaultMessageInterpolator;
import org.apache.bval.jsr303.extensions.MethodValidator;
import org.aspectj.lang.reflect.MethodSignature;

public aspect Validations
{
   private static final ValidatorFactory factory;

   static
   {
      ProviderSpecificBootstrap<ApacheValidatorConfiguration> provider = Validation
         .byProvider(ApacheValidationProvider.class);

      ApacheValidatorConfiguration configuration = provider.configure();
      configuration.addProperty(ApacheValidatorConfiguration.Properties.VALIDATOR_FACTORY_CLASSNAME,
         ApacheValidatorFactory.class.getName());
      configuration.ignoreXmlConfiguration();
      configuration.messageInterpolator(new DefaultMessageInterpolator()
      {
         @Override
         public String interpolate(String message, Context context, Locale locale)
         {
            return super.interpolate(message, context, new Locale(""));
         }
      });

      factory = configuration.buildValidatorFactory();
   }

   private MethodValidator getMethodValidator()
   {
      return factory.getValidator().unwrap(MethodValidator.class);
   }

   pointcut validateMethodReturnedValue() : execution(@javax.validation.constraints.* !void *(..));

   pointcut validateMethodArguments() : execution(* *(..,@javax.validation.constraints.* (*),..));

   @SuppressWarnings({"unchecked", "rawtypes"})
   after() returning(Object returnedValue): validateMethodReturnedValue()
   {
      final MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
      final Class clazz = methodSignature.getDeclaringType();
      final Method method = methodSignature.getMethod();
      final Set<ConstraintViolation<?>> violations = getMethodValidator().validateReturnedValue(clazz, method,
         returnedValue);
      if (!violations.isEmpty())
      {
         final StringBuilder sb = new StringBuilder();
         sb.append("Constraint violation(s) at ");
         sb.append(clazz.getSimpleName());
         sb.append("#");
         sb.append(method.getName());
         sb.append(": ");
         for (ConstraintViolation<?> violation : violations)
         {
            sb.append("Returned value ");
            sb.append(violation.getMessage());
            sb.append("; ");
         }
         sb.deleteCharAt(sb.length() - 2);
         throw new ConstraintViolationException(sb.toString(), violations);
      }
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   before() : validateMethodArguments()
   {
      final MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
      final Class clazz = methodSignature.getDeclaringType();
      final Method method = methodSignature.getMethod();
      final Object[] args = thisJoinPoint.getArgs();

      StringBuilder sb = null;
      Set<ConstraintViolation<?>> violations = null;
      for (int i = 0; i < args.length; i++)
      {
         final Object arg = args[i];
         final Set<ConstraintViolation<?>> violationSet = getMethodValidator().validateParameter(clazz, method, arg, i);
         if (!violationSet.isEmpty())
         {
            if (sb == null)
            {
               violations = new LinkedHashSet<ConstraintViolation<?>>();
               sb = new StringBuilder();
               sb.append("Constraint violation(s) at ");
               sb.append(clazz.getSimpleName());
               sb.append("#");
               sb.append(method.getName());
               sb.append(": ");
            }
            violations.addAll(violationSet);
            for (ConstraintViolation<?> violation : violationSet)
            {
               sb.append("Argument ");
               sb.append(i);
               sb.append(" ");
               sb.append(violation.getMessage());
               sb.append("; ");
            }
         }
      }

      if (violations != null)
      {
         sb.deleteCharAt(sb.length() - 2);
         throw new ConstraintViolationException(sb.toString(), violations);
      }
   }
}
