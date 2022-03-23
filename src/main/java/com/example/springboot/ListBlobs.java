//package com.example.springboot;
//
//import java.io.IOException;
//
//import io.github.cdimascio.dotenv.Dotenv;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.azure.identity.DefaultAzureCredential;
//import com.azure.identity.DefaultAzureCredentialBuilder;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobContainerClientBuilder;
//import com.azure.storage.blob.models.BlobItem;
//
//import io.swagger.v3.oas.annotations.Operation;
//
//@RestController
//public class ListBlobs {
//	
//	Dotenv dotenv = Dotenv.configure()
//			  		.directory("c:/Dev/Workspace/mySpringBootApp/initial")
//			  		.load();
//
//	@Operation(summary = "This is a list of available Blobs")
//	@GetMapping("/myBlobs")
//	public void listAvailableBlobs() throws IOException {
//		// define here the logic to interact with blob storage.
//		
//        DefaultAzureCredential defaultCredential = new DefaultAzureCredentialBuilder().build();
//
//        System.out.println(defaultCredential);
//        
//		// Create a BlobServiceClient object which will be used to create a container client
//        BlobContainerClient blobClient = new BlobContainerClientBuilder()
//                .credential(defaultCredential)
//                .endpoint(dotenv.get("BlobContainerEndpoint"))
//                .buildClient();
//
//        // List the blob(s) in the container.
//        // ok good working now. note that feeds is a container and it lists all blobs into it.
//        for (BlobItem blobItem : blobClient.listBlobs()) {
//            System.out.println("\t" + blobItem.getName());
//        }
//	}
//	
//	
//}
