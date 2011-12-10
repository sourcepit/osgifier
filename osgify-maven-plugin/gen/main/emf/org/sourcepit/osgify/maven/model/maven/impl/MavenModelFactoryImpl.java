/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.maven.model.maven.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.osgify.maven.model.maven.*;
import org.sourcepit.osgify.maven.model.maven.MavenArtifact;
import org.sourcepit.osgify.maven.model.maven.MavenDependency;
import org.sourcepit.osgify.maven.model.maven.MavenModelFactory;
import org.sourcepit.osgify.maven.model.maven.MavenModelPackage;
import org.sourcepit.osgify.maven.model.maven.MavenProject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MavenModelFactoryImpl extends EFactoryImpl implements MavenModelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static MavenModelFactory init()
   {
      try
      {
         MavenModelFactory theMavenModelFactory = (MavenModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.sourcepit.org/osgify/maven/0.1"); 
         if (theMavenModelFactory != null)
         {
            return theMavenModelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new MavenModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenModelFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case MavenModelPackage.MAVEN_ARTIFACT: return createMavenArtifact();
         case MavenModelPackage.MAVEN_DEPENDENCY: return createMavenDependency();
         case MavenModelPackage.MAVEN_PROJECT: return createMavenProject();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenArtifact createMavenArtifact()
   {
      MavenArtifactImpl mavenArtifact = new MavenArtifactImpl();
      return mavenArtifact;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenDependency createMavenDependency()
   {
      MavenDependencyImpl mavenDependency = new MavenDependencyImpl();
      return mavenDependency;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenProject createMavenProject()
   {
      MavenProjectImpl mavenProject = new MavenProjectImpl();
      return mavenProject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MavenModelPackage getMavenModelPackage()
   {
      return (MavenModelPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static MavenModelPackage getPackage()
   {
      return MavenModelPackage.eINSTANCE;
   }

} // MavenModelFactoryImpl
