package com.ajsw.barGeolocation;

import com.ajsw.barGeolocation.domain.dto.Bar;
import com.ajsw.barGeolocation.domain.entity.BarEntity;
import com.ajsw.barGeolocation.repository.IBarRepository;
import com.ajsw.barGeolocation.service.impl.BarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BarServiceTest {
    @Mock
    private IBarRepository barRepository;
    @InjectMocks
    private BarService barService;

    @Test
    public void testGetById(){

        BarEntity barEntity = CreateBarEntity();
        Mockito.when(barRepository.findById(1)).thenReturn(Optional.ofNullable(barEntity));

        Bar result = barService.getById(1);
        Bar expected = CreateBar();

        Assertions.assertEquals(expected, result);

    }
    public Bar CreateBar(){
        Bar bar = new Bar();
        bar.setId(1);
        bar.setName("Baum");
        bar.setDescription("Cervecería Artesanal");
        bar.setLogo("https://acdn.mitiendanube.com/stores/001/246/405/themes/common/logo-1526103796-1593000923-2de6ea67c085ea0f2d042a0765efb9f91593000923.png");
        bar.setLatitude(-34.781990279618825);
        bar.setLongitude(-58.26463106629505);
        return  bar;
    }

    public BarEntity CreateBarEntity(){
        BarEntity barEntity = new BarEntity();

        barEntity.setId(1);
        barEntity.setName("Baum");
        barEntity.setDescription("Cervecería Artesanal");
        barEntity.setLogo("https://acdn.mitiendanube.com/stores/001/246/405/themes/common/logo-1526103796-1593000923-2de6ea67c085ea0f2d042a0765efb9f91593000923.png");
        barEntity.setLatitud(-34.781990279618825);
        barEntity.setLongitud(-58.26463106629505);

        return  barEntity;
    }

}
